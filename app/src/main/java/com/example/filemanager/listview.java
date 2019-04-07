package com.example.filemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class listview extends Fragment {

    EditText txtview;
    ViewGroup.LayoutParams params ;
    LinearLayout txtframe;
    Button txtbut;
    ListView list;
    operations op = new operations();
    LinearLayout advance;
    String temp;
    EditText input;
    Button search;
    String[] name;
    String[] path;
    Integer[] image;
    listadapter  adapt;
    int flags=0;
    int flag=1;
    int flagtxt=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.actuallist,null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        advance=(LinearLayout)view.findViewById(R.id.advance);
        input=(EditText)view.findViewById(R.id.input);
        search=(Button)view.findViewById(R.id.searchD);
        txtbut=(Button)view.findViewById(R.id.save);
        txtframe=(LinearLayout)view.findViewById(R.id.txt_doc);
        txtview=(EditText)view.findViewById(R.id.txt_id);

        ViewGroup.LayoutParams para = advance.getLayoutParams();


        input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                input.setFocusableInTouchMode(true);
                input.setFocusable(true);
                return false;
            }
        });


       list=(ListView)view.findViewById(R.id.actual_list);
      op.opera();
       update();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int t=op.inside(position);

                if(t==0)
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
                    dlgAlert.setMessage("Press pause to pause.");
                    dlgAlert.setTitle("Playing music");
                    dlgAlert.setPositiveButton("pause", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            op.pause();
                        }
                    });

                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
                else if(t==1)
                {
                    update();
                }
                else if(t==3)
                {
                    readtxt();
                    flagtxt=1;
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp=input.getText().toString();
               if(flags==1)
               {
                   if(!(temp.isEmpty()))
                   {
                       op.advance_search(temp,1);
                       name=op.namel.toArray(new String[0]);
                       image = op.image1.toArray(new Integer[0]);
                       path=op.matchar.toArray(new String[0]);
                       adapt = new listadapter(getActivity(),name,path,image);
                       list.setAdapter(adapt);


                   }


               }
                else if(!(temp.isEmpty()))
                {
                    op.search(temp,1);
                        update();
                }
            }
        });
    }

    public void update()
    {
        name=op.namel.toArray(new String[0]);
        image = op.image1.toArray(new Integer[0]);
        path=op.pathl.toArray(new String[0]);
        adapt = new listadapter(getActivity(),name,path,image);
        list.setAdapter(adapt);

    }

    public void advanceSearch()
    {
        if(flags==0)
        {
            flags=1;
            Toast.makeText(getActivity(),"Advance search is activated",Toast.LENGTH_LONG).show();

        }
        else
        {
            flags=0;
            Toast.makeText(getActivity(),"Advance search is diactivated",Toast.LENGTH_LONG).show();
        }

    }

    public void image()
    {
        op.image();
        update();
    }

    public void vidios()
    {
        op.video();
        update();
    }

    public void audios()
    {
        op.Audio();
        update();
    }

    public void pdf()
    {
        op.pdf();
        update();
    }

    public void doc()
    {
        op.doc();
        update();
    }
    public void all()
    {
        flag=1;
        op.opera();
        update();
    }

    public void back()
    {
        if(flagtxt==1)
        {
            params = txtframe.getLayoutParams();
            params.height = 1;
            txtframe.setLayoutParams(params);
            flagtxt=0;

        }
        else
        {
            flag=1;
            op.back();
            update();
        }


    }


    public  void readtxt()
    {

        String[] temp={};
        Integer[] temp1={ };

        adapt = new listadapter(getActivity(),temp,temp,temp1);
        list.setAdapter(adapt);

        params = txtframe.getLayoutParams();
        params.height = 1000;
        txtframe.setLayoutParams(params);
        flagtxt=0;
        String t = " ";
        String line = t ;

        try {

            File file = new File(op.txtpath);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {

        }


        txtview.setText(line);
        Toast.makeText(getActivity(),"Txt document opened",Toast.LENGTH_SHORT).show();
    }


}
