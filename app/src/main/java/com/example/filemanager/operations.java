package com.example.filemanager;

import android.media.MediaPlayer;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class operations {


    File root;
    String[] name;
    String[] path;
    String[] size;
    Integer[] image;
    String txtpath;
    int temp;
    int n;
    List<Integer> count = new ArrayList<Integer>();
    MediaPlayer mediaPlayer = null;

    int top = -1;
    List<String> stacklist = new ArrayList<String>();
    List<String> namel = new ArrayList<String>();
    List<String> pathl = new ArrayList<String>();
    List<Integer> image1 = new ArrayList<Integer>();

    List<String> imagenamel = new ArrayList<String>();
    List<String> imagepathl = new ArrayList<String>();
    List<Integer> imageimage1 = new ArrayList<Integer>();


    List<String> videonamel = new ArrayList<String>();
    List<String> videopathl = new ArrayList<String>();
    List<Integer> videoimage1 = new ArrayList<Integer>();


    List<String> audionamel = new ArrayList<String>();
    List<String> audiopathl = new ArrayList<String>();
    List<Integer> audioimage1 = new ArrayList<Integer>();

    List<String> pdfnamel = new ArrayList<String>();
    List<String> pdfpathl = new ArrayList<String>();
    List<Integer> pdfimage1 = new ArrayList<Integer>();

    List<String> docnamel = new ArrayList<String>();
    List<String> docpathl = new ArrayList<String>();
    List<Integer> docimage1 = new ArrayList<Integer>();

    List<String> txtnamel = new ArrayList<String>();
    List<String> txtpathl = new ArrayList<String>();
    List<Integer> txtimage1 = new ArrayList<Integer>();
    List<String> matchar = new ArrayList<String>();

    int flags=0;

    File[] files;

    public void opera() {

        flags=0;
        namel.clear();
        pathl.clear();
        image1.clear();
        top=-1;
        stacklist.clear();
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        top = top + 1;
        stacklist.add(root.getPath());
        files = root.listFiles();
       decide();
       take();
    }


    public void take()
    {
        int top1 = 0;
        File roo = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        String[] stack = new String[100];
        top1 = top1 + 1;
        stack[top1] = roo.getAbsolutePath();

        while (top1 > 0 && top1<100) {

            roo = new File(stack[top1]);
            top1=top1-1;
            files = roo.listFiles();
            if (files != null) {
              for (File file : files) {

                   if ((file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".JPG"))) {
                        this.imagenamel.add(file.getName());
                        this.imagepathl.add(file.getPath());
                        this.imageimage1.add(R.drawable.image_icon);
                    } else if(file.isDirectory()) {
                        if(top1<99)
                        {
                            top1=top1+1;
                            stack[top1]=file.getAbsolutePath();
                        }
                    }
                    else if (file.getName().endsWith(".mp3")) {
                        this.audionamel.add(file.getName());
                        this.audiopathl.add(file.getPath());
                        this.audioimage1.add(R.drawable.music_icon);
                    } else if (file.getName().endsWith(".mp4")) {
                        this.videonamel.add(file.getName());
                        this.videopathl.add(file.getPath());
                        this.videoimage1.add(R.drawable.vidoe);
                    }
                    else if (file.getName().endsWith(".pdf")) {
                        this.pdfnamel.add(file.getName());
                        this.pdfpathl.add(file.getPath());
                        this.pdfimage1.add(R.drawable.pdf);
                    }
                    else if (file.getName().endsWith(".doc")||file.getName().endsWith(".docx")) {
                        this.docnamel.add(file.getName());
                        this.docpathl.add(file.getPath());
                        this.docimage1.add(R.drawable.doc);
                    }
                }


            }


        }


    }

    public void image()
    {
        flags=1;
        top = top + 1;
        stacklist.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        namel.clear();
        image1.clear();
        pathl.clear();
        int i=0;
        for(String name : imagenamel)
        {

            namel.add(name);
            pathl.add(imagepathl.get(i));
            image1.add(imageimage1.get(i));
            i=i+1;
        }
    }

    public void pdf()
    {
        flags=3;
        top = top + 1;
        stacklist.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        namel.clear();
        image1.clear();
        pathl.clear();
        int i=0;
        for(String name : pdfnamel)
        {

            namel.add(name);
            pathl.add(pdfpathl.get(i));
            image1.add(pdfimage1.get(i));
            i=i+1;
        }
    }

    public void doc()
    {
        flags=4;
        top = top + 1;
        stacklist.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        namel.clear();
        image1.clear();
        pathl.clear();
        int i=0;
        for(String name : docnamel)
        {

            namel.add(name);
            pathl.add(docpathl.get(i));
            image1.add(docimage1.get(i));
            i=i+1;
        }
    }

    public void video()
    {
        flags=2;
        top = top + 1;
        stacklist.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        namel.clear();
        image1.clear();
        pathl.clear();
        int i=0;
        for(String name : videonamel)
        {

            namel.add(name);
            pathl.add(videopathl.get(i));
            image1.add(videoimage1.get(i));
            i=i+1;
        }
    }


    public void Audio()
    {
        flags=5;
        top = top + 1;
        stacklist.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        namel.clear();
        image1.clear();
        pathl.clear();
        int i=0;
        for(String name : audionamel)
        {

            namel.add(name);
            pathl.add(audiopathl.get(i));
            image1.add(audioimage1.get(i));
            i=i+1;
        }
    }



    public int inside(int position)
    {
        File temp = new File(pathl.get(position));
        if(temp.isDirectory())
        {
            top = top + 1;
            stacklist.add(root.getPath());
            root=new File(pathl.get(position));
            files=root.listFiles();
            decide();
            return 1;
        }
        else
        {
            int i;
            i=play(position);
            return i;
        }

    }

    public int play(int position)
    {
            File temp = new File(pathl.get(position));
            if(temp.getName().endsWith(".mp3"))
            {
                try
                {

                        pause();
                        mediaPlayer.setDataSource(pathl.get(position));
                        mediaPlayer.prepare();
                        mediaPlayer.start();


                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                    return 0;
            }
            else if(temp.getName().endsWith(".txt"))
            {
                txtpath=pathl.get(position);
                return 3;
            }
            return 2;
    }

    public void pause()
    {
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }

        }
        else
        {
            mediaPlayer = new  MediaPlayer();
        }
    }

    public void back()
    {
        flags=0;
        pause();
        if(top>=0)
        {
            root=new File(stacklist.get(top));
            stacklist.remove(top);
            top=top-1;
            files=root.listFiles();
            decide();
        }
    }



    public void search(String value,int i)
    {

        if(flags==1)
            image();
        else if(flags==2)
            video();
        else if (flags==3)
            pdf();
        else if (flags==4)
            doc();
        else if (flags==5)
            Audio();


        top=top+1;
        stacklist.add(root.getPath());
        value=value.toLowerCase();

        if(i==1)
        {

            List<String> temp1 = new ArrayList<String>(namel);
            List<String> temp2 = new ArrayList<String>(pathl);
            List<Integer> temp3 = new ArrayList<Integer>(image1);

            n=0;
            temp=temp1.size();
            namel.clear();
            pathl.clear();
            image1.clear();
            while(n<temp)
            {
                if(temp1.get(n).toLowerCase().startsWith(value))
                {
                    namel.add(temp1.get(n));
                    pathl.add(temp2.get(n));
                    image1.add(temp3.get(n));
                }
                n=n+1;

            }
        }

    }

    public void advance_search(String value,int i)
    {

        txtimage1.clear();
        txtnamel.clear();
        txtpathl.clear();



        top=top+1;
        stacklist.add(root.getAbsolutePath());


        int top1 = 0;
        File roo;
        String[] stack = new String[100];
        top1 = top1 + 1;
        stack[top1] = root.getAbsolutePath();

        while (top1 > 0 && top1<99) {

            roo = new File(stack[top1]);
            top1=top1-1;
            files = roo.listFiles();
            if (files != null) {
                for (File file : files) {


                    if (file.isDirectory()) {
                        if(top1<99)
                        {
                            top1=top1+1;
                            stack[top1]=file.getAbsolutePath();
                        }
                    }
                    else if(file.getName().endsWith(".txt"))
                    {
                        txtnamel.add(file.getName());
                        txtpathl.add(file.getAbsolutePath());
                        txtimage1.add(R.drawable.txt_icon);


                    }
                }


            }


        }
        trial(value);
    }


    public void trial(String value)
    {
        namel.clear();
        image1.clear();
        pathl.clear();
        matchar.clear();
        int max=0;
        int i=0;
        int[] match = new int[200];

        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        File fp;
        String temp=" ";
        int count;

        List<String> temppath = new ArrayList<String>();
        List<String> tempname = new ArrayList<String>();


        String line;
        for(String path : txtpathl)
        {
            try {

                fp = new File(path);
                FileInputStream fileInputStream = new FileInputStream(fp);
                inputStreamReader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                stringBuilder = new StringBuilder();
                line=temp;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + System.getProperty("line.separator"));
                }
                fileInputStream.close();
                line = stringBuilder.toString();
                count=count(line,value);

                if(count>max)
                    max=count;

                tempname.add(fp.getName());
                match[i]=count;
                i=i+1;
                temppath.add(path);
                image1.add(R.drawable.txt_icon);
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {

            }


        }

        for(;max>=0;max=max-1)
        {
            for(int j=0;j<i;j++)
            {
                if(match[j]==max)
                {
                    namel.add(tempname.get(j));
                    pathl.add(temppath.get(j));
                    matchar.add("Character Match : "+match[j]);
                    image1.add(R.drawable.txt_icon);
                }
            }

        }






    }

    public int count(String line,String value)
    {
        int temp=0;
        int i=0,p=0;
        while(i<line.length())
        {
            p=0;
            while(i<line.length()&&p<value.length())
            {
                if(line.charAt(i)==value.charAt(p))
                {
                    i=i+1;
                    p=p+1;
                }
                else
                {
                    i=i+1;
                    break;
                }

            }
            if(p>temp)
            {
                temp=p;
            }

        }
        return temp;
    }


    public void decide() {
        if (files != null) {
            this.namel.clear();
            this.pathl.clear();
            this.image1.clear();
            for (File file : files) {
                this.namel.add(file.getName());
                this.pathl.add(file.getPath());
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".JPG")) {
                    this.image1.add(R.drawable.image_icon);
                } else if (file.isDirectory()) {
                    this.image1.add(R.drawable.folder);
                } else if (file.getName().endsWith(".apk") || file.getName().endsWith(".APK")) {
                    this.image1.add(R.drawable.apk_icon);
                } else if (file.getName().endsWith(".zip")) {
                    this.image1.add(R.drawable.zip_icon);
                } else if (file.getName().endsWith(".txt")) {
                    this.image1.add(R.drawable.txt_icon);
                } else if (file.getName().endsWith(".mp3")) {
                    this.image1.add(R.drawable.music_icon);
                } else if (file.getName().endsWith(".mp4")){
                    this.image1.add(R.drawable.vidoe);
            }else if (file.getName().endsWith(".pdf")) {
                    this.image1.add(R.drawable.pdf);
                }
                else if (file.getName().endsWith(".doc")||file.getName().endsWith(".docx")) {
                    this.image1.add(R.drawable.doc);
                }else {
                    this.image1.add(R.drawable.else_icon);
                }
            }


        }


    }

}
