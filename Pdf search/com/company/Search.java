package com.company;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {
    public static  void search(String add,String text)
    {
        Path dir=Path.of(add);

        String txt="text/plain";
        String pdf="application/pdf";
        List <Path> stack=new ArrayList<>();
        stack.add(dir);
        while(stack.size()>0){
        Path top=stack.get(0);
        stack.remove(0);

        try {
            if(!Files.isDirectory(top, LinkOption.NOFOLLOW_LINKS)) {
                //System.out.println(top.toString());
                if (Objects.equals(txt, Files.probeContentType(top)) && Files.readString(top).contains(text))
                {//System.out.println(top.getFileName());
                 System.out.println(top.toString());
                }

                else if(Objects.equals(pdf, Files.probeContentType(top)))
                {
                    PDDocument document = PDDocument.load(new File(top.toString()));// here file1.pdf is the name of pdf file which we want to read....
                    document.getClass();
                    if (!document.isEncrypted())
                    {
                        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                        stripper.setSortByPosition(true);
                        PDFTextStripper Tstripper = new PDFTextStripper();
                        String str = Tstripper.getText(document);
                        Scanner scn = null;
                        scn = new Scanner(str);
                        String line="";
                        while (scn.hasNextLine())
                        {
                            line = scn.nextLine();
                            if(line.contains(text))
                            {
                                System.out.println(top.toString());
                                break;
                            }
                        }
                    }
                    document.close();
                }
            }

            else {
                Stream <Path> path=Files.list(top).filter((p)->{
                    try {
                        if(Objects.equals(txt, Files.probeContentType(p)) ||Objects.equals(pdf, Files.probeContentType(p))|| Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS))
                        return true;

                        else
                            return false;

                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                });
                List<Path> path1 =path.collect(Collectors.toList());
                stack.addAll(path1);
                //List<Path> path = Files.list(dir).collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }}
    }
}
