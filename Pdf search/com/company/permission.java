package com.company;

import java.io.*;
import java.util.Random;

public class permission {


    public class OS {


        //Core data structures for Operating System

        private char [][]memory= new char[300][4];
        private char []buffer= new char[40];
        private char []R=new char[4];
        private char []IR=new char[4];
        private int IC;
        private int T;
        private int SI;
        private int TI;
        private int PI;
        private int ptr;
        private boolean validbit;



        //Non core data structures
        private int memory_used;
        private int pageTable_used;
        private int data_card_skip=0;
        private String input_file;
        private String output_file;
        private FileReader input;
        private BufferedReader fread;
        private FileWriter output;
        private BufferedWriter fwrite;
        private Random rand;

        public OS(String file,String output){
            this.input_file=file;
            this.SI=0;
            try {
                this.input = new FileReader(file);
                this.fread= new BufferedReader(input);
                this.output=new FileWriter(output);
                //this.fwrite= new BufferedWriter(this.output);
                //fwrite.write("Svsndjd");
                //fwrite.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void LOAD()
        {
            int flag=0;
            String line;
            try {

                while((line=fread.readLine()) != null)
                {
                    buffer=line.toCharArray();
                    if(buffer[0]=='$'&& buffer[1]=='A'&&buffer[2]=='M'&& buffer[3]=='J') {
                        System.out.println("Program card detected");
                        init();
                        int linel=Integer.parseInt(line.substring(8,12));
                        int time=Integer.parseInt(line.substring(12,16));
                        //System.out.println(linel+"chal");
                        //System.out.println(time);
                       // pcb=new PCB(linel,time);

                        continue;
                    }
                    else if(buffer[0]=='$'&& buffer[1]=='D'&&buffer[2]=='T'&& buffer[3]=='A')
                    {
                        System.out.println("Data card detected");
                        execute();
                        flag=2;
                        continue;
                    }
                    else if(buffer[0]=='$'&& buffer[1]=='E'&&buffer[2]=='N'&& buffer[3]=='D')
                    {
                        System.out.println("END card detected");
                        output.write("\n\n\n");
                        print_memory();
                        continue;
                    }
                    if(memory_used==100)
                    {   //abort;
                        System.out.println("Abort due to exceed memory usage");
                    }
                    //System.out.println(line.length());
                    System.out.println(line);

                    //System.out.println("Mai load hora");
                    if(flag!=2) {

                        System.out.println("ur program starts here");
                        int a = allocate();
                        char[] chars = ("" + a/10).toCharArray();
                        ++pageTable_used;
                        memory[ptr*10+pageTable_used][0]=chars[0];
                        //System.out.println(memory[ptr*10+pageTable_used][0]);
                        //System.out.println(pageTable_used+" "+ptr+" a is "+a);
                        if(chars.length>1)
                            memory[ptr*10+pageTable_used][1]=chars[1];
                        for (int i = 0; i < line.length(); ) {
                            //System.out.println(buffer[i]);
                            memory[a][i % 4] = buffer[i];
                            i++;
                            if (i % 4 == 0)
                                a++;
                        }


                    }

                }
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(memory_used);
            //print_memory();
        }
        private void execute(){


            while(1<2)
            {
                // System.out.println(IC);
                int ic=map(IC);
                System.out.println(IC +" is this" +ic);
                IR[0]=memory[ic][0];
                IR[1]=memory[ic][1];
                IR[2]=memory[ic][2];
                IR[3]=memory[ic][3];
                System.out.println(new String(IR));
                if(IC==100)
                    break;

                IC++;

                if(IR[0]=='G' && IR[1]=='D')
                {
                    String line = new String(IR);
                    int num=Integer.parseInt(line.substring(2));
                    //System.out.println(num +"Sahi hai");;
                    validbit=true;
                    ic=map(num);
                    if(ic==-1)
                        masterMode();
                    SI=1;
                    masterMode();
                }
                else if(IR[0]=='H')
                {
                    SI=3;
                    //System.out.println("Gel ka");
                    break;
                    //masterMode();
                }
            }

        }

        private void masterMode() {
            int i=this.SI;
            if(PI==3 && TI==0 && validbit==true)
            {
                IC--;
                //int a=allocate();
                //System.out.println(a +"is new allocated");
            }
            if(i==1)
            {
                Read();
            }
            else if(i==2)
            {
                Write();
            }
            else if(i==3)
            {

            }
            SI=0;
        }

        private void Write() {
            IR[3]='0';
            String line = new String(IR);
            int num=Integer.parseInt(line.substring(2));
            String t,total="";
            for(int i=0;i<10;i++)
            {
                t=new String(memory[num+i]);
                total=total.concat(t);
            }
            System.out.println(total+"In write");
            try {
                output.write(total);
                output.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void Read() {
            int flag=0;
            IR[3]='0';
            String line = new String(IR);

            int num=Integer.parseInt(line.substring(2));


            try {
                line=fread.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer=line.toCharArray();
            for (int i = 0; i < line.length();) {
                //System.out.println(buffer[i]);
                memory[num][(i%4)]=buffer[i];
                i++;
                if(i%4==0)
                    num++;
            }
        }

        public void init(){
            memory_used=0;
            memory=null;
            memory= new char[300][4];
            data_card_skip=0;
            T=0;
            TI=0;
            pageTable_used=-1;
            IC=0;
            ptr=(int)(Math.random()*30);
        }
        public void print_memory(){
            for(int i=0;i<300;i++) {
                System.out.println("memory["+i+"] "+new String(memory[i]));
            }
            System.out.println("Your Ptr is "+ptr);
        }

        private int allocate(){
            int a;
            while(1<2)
            {
                a=(int)(Math.random()*30);
                if(memory[a*10][0]=='\0' && a!=ptr)
                    break;
            }
            memory[ptr][0]=(char)(a/10);
            memory[ptr][1]=(char)(a%10);
            System.out.println(a*10+"is allocated");
            return a*10;
        }

        private int map(int va){
            if(va>100)
            {
                PI=2;
                return -1;

            }

            int pte=ptr*10+va/10;
            int fNo;
            String line = new String(memory[pte]);
            //System.out.println(line +"This is line");
            if(memory[pte][0]=='\0') {
                PI = 3;
                return -1;
            }
            else if(line.length()>1)
                fNo=Integer.parseInt(line.substring(0,2));
            else
                fNo=Integer.parseInt(line.substring(0,1));

            int ra=fNo*10+va%10;

            return ra;

        }
    }

}
