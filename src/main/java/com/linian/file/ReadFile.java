package com.linian.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;



public class ReadFile {
    public static void main(String[] args) throws IOException {
        String inputFile = "data/file/inputFile";
        String outputFile = "data/file/outputFile";

        List<String> outputLines = new ArrayList<>();

        ReadFile readers = new ReadFile();

        List<String> lines = readers.getInputLines(inputFile);
        System.out.println(lines);
        System.out.println(lines.size());


        for(String line: lines) {
            // System.out.println(line);
            String[] parts = line.split(",");

            System.out.println(parts[0] + " " + parts[1]);
            // Date date = new Date();
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

            Date date = null;
            try {
                date = sdf.parse(parts[1]);
            } catch (final ParseException e) {

            }

            System.out.println(date);

            Date d2=null;
            try {
                d2 = sdf.parse("2020-08-21 23:42:19.901");
            } catch (final ParseException e) {

            }
            System.out.println(d2 + " " + date + " " + (d2.equals(date)) + "\n"
                    + d2.getTime() + " " + date.getTime());
            // System.out.println(d2 + " " + (d2.equals(date)));

            outputLines.add(d2 + " " + (d2.equals(date)));

            // System.out.println(sdf.format(date));

            System.out.println(isSameTimestamp(d2.getTime(), date.getTime()));
        }

        Files.write(Paths.get(outputFile), outputLines);
    }

    private List<String> getInputLines(String inputFile) throws IOException {
//        Path path = Paths.get(inputFile).toAbsolutePath();
//        System.out.println(path);

        try (final Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            return stream.collect(Collectors.toList());
        } catch (final IOException e) {
            throw new IOException("Fail to open file:" + inputFile, e);
        }
    }

    private static boolean isSameTimestamp(final long timestamp1, final long timestamp2) {
        final long MAX_TIMESTAMP_DELTA = 10000000;
        return Math.abs(timestamp1 - timestamp2) < MAX_TIMESTAMP_DELTA;
    }
}
