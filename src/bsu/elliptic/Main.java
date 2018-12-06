package bsu.elliptic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inFilePath = "in.txt";
        String outFilePath = "out.txt";
        if (args.length == 1) {
            inFilePath = args[0];
        }

        if (args.length == 2) {
            outFilePath = args[1];
        }

        File inFile = new File(inFilePath);
        File outFile = new File(outFilePath);
        try {
            Scanner sc = new Scanner(inFile);
            FileWriter fileWriter = new FileWriter(outFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String lineArr [] = line.split(" ");
                try {
                    EllipticCurve curve = new EllipticCurve(
                            Integer.valueOf(lineArr[0]),
                            Integer.valueOf(lineArr[1]),
                            Integer.valueOf(lineArr[2]));
                    for (Point p : curve.getPoints()) {
                        fileWriter.write(p.toString() + " " + curve.getOrder(p) + "\n");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Ошибка в строке " + line);
                } catch (IOException e) {
                    System.out.println("Ошибка при записи строки " + line);
                }
            }
            sc.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
