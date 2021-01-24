package com.company;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

        import java.io.*;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] mips = new char[32];
        for (int i = 0; i < 32; i++) {
            mips[i] = '0';
        }

        String functionName, rt, rd, rs, immediate, shamt, funct, address,
                offset;
        String rsi, rti, rdi, functi, shamti;
        int offseti, immediatei;
        char type;

        HashMap<String, String> opcodemap = new HashMap<String, String>();
        opcodemap.put("add", "000000");
        opcodemap.put("addi", "001000");
        opcodemap.put("slt", "000000");
        opcodemap.put("slti", "001010");
        opcodemap.put("sll", "000000");
        opcodemap.put("sw", "101011");
        opcodemap.put("lw", "100011");
        opcodemap.put("move", "000000");  //move a0 s2 = add a0 s2 0
        opcodemap.put("beq", "000100");
        opcodemap.put("bne", "000101");
        opcodemap.put("jal", "000011");
        opcodemap.put("j", "000010");
        opcodemap.put("jr", "000000");

//System.out.println(opcodemap.get("sw"));

        HashMap<String, Character> typemap = new HashMap<String,
                Character>();
        typemap.put("add", 'r');
        typemap.put("addi", 'i');
        typemap.put("slt", 'r');
        typemap.put("slti", 'i');
        typemap.put("sll", 'r');
        typemap.put("sw", 'i');
        typemap.put("move", 'r');
        typemap.put("beq", 'i');
        typemap.put("bne", 'i');
        typemap.put("lw", 'i');
        typemap.put("jal", 'j');
        typemap.put("j", 'j');
        typemap.put("jr", 'r');

        HashMap<String, String> rfunctionmap = new HashMap<String, String>();
//r types have unique functions
        rfunctionmap.put("add", "100000");
        rfunctionmap.put("slt", "101010");
        rfunctionmap.put("sll", "000000");
        rfunctionmap.put("move", "100000");
        rfunctionmap.put("jr", "001000");


        HashMap<String, String> registermap = new HashMap<String, String>();
// Hash register table to put the values of the registers
        registermap.put("$zero", "00000");
        registermap.put("$0", "00000");
        registermap.put("$v0", "00010");
        registermap.put("$v1", "00011");
        registermap.put("$a0", "00100");
        registermap.put("$a1", "00101");
        registermap.put("$a2", "00110");
        registermap.put("$a3", "00111");
        registermap.put("$t0", "01000");
        registermap.put("$t1", "01001");
        registermap.put("$t2", "01010");
        registermap.put("$t3", "01011");
        registermap.put("$t4", "01100");
        registermap.put("$t5", "01101");
        registermap.put("$t6", "01110");
        registermap.put("$t7", "01111");
        registermap.put("$t8", "11000");
        registermap.put("$t9", "11001");
        registermap.put("$s0", "10000");
        registermap.put("$s1", "10001");
        registermap.put("$s2", "10010");
        registermap.put("$s3", "10011");
        registermap.put("$s4", "10100");
        registermap.put("$s5", "10101");
        registermap.put("$s6", "10110");
        registermap.put("$s7", "10111");
        registermap.put("$sp", "11101");
        registermap.put("$ra", "11111");
        registermap.put("$pc", "01000");
        //for split issues
        registermap.put("$zero,", "00000");
        registermap.put("$0,", "00000");
        registermap.put("$v0,", "00010");
        registermap.put("$v1,", "00011");
        registermap.put("$a0,", "00100");
        registermap.put("$a1,", "00101");
        registermap.put("$a2,", "00110");
        registermap.put("$a3,", "00111");
        registermap.put("$t0,", "01000");
        registermap.put("$t1,", "01001");
        registermap.put("$t2,", "01010");
        registermap.put("$t3,", "01011");
        registermap.put("$t4,", "01100");
        registermap.put("$t5,", "01101");
        registermap.put("$t6,", "01110");
        registermap.put("$t7,", "01111");
        registermap.put("$t8,", "11000");
        registermap.put("$t9,", "11001");
        registermap.put("$s0,", "10000");
        registermap.put("$s1,", "10001");
        registermap.put("$s2,", "10010");
        registermap.put("$s3,", "10011");
        registermap.put("$s4,", "10100");
        registermap.put("$s5,", "10101");
        registermap.put("$s6,", "10110");
        registermap.put("$s7,", "10111");
        registermap.put("$sp,", "11101");
        registermap.put("$ra,", "11111");
        registermap.put("$pc,", "01000");


        int selection = 0;

        while(selection != 3)
        {
            System.out.println("\n==MAIN MENU==\n1. Interactive Mode\n2. Batch Mode\n3. Exit\n-->Enter Selection:  ");
            selection = scanner.nextInt();

            if (selection == 1)
            {
                System.out.println("\nEnter how many instruction you want to input");
                int intsructionnumber= scanner.nextInt();
                String inst = scanner.nextLine();
                String[] instruction;
                int k=0;
                while(k<intsructionnumber){
                    System.out.println("\nEnter instruction: ");
                    inst = scanner.nextLine();
                    instruction = inst.split(" "); // split method to separate every string according to space

                    // System.out.println("output string: " +
                    Arrays.toString(instruction);

                    while (!(opcodemap.containsKey(instruction[0]))) {
                        System.out.println("You entered invalid entry!");
                        System.out.println("Enter instruction: ");
                        inst = scanner.nextLine();
                        instruction = inst.split(" ");
                    }
                    if(instruction[0].equals("Label:")){
                        functionName=instruction[1];
                        // System.out.println("function name: "+functionName);
                    }
                    else{
                        functionName = instruction[0];
                        //System.out.println("function name: " + functionName);}

                        type = typemap.get(functionName);
                        //System.out.println("type: " + type);

                        if (type == 'r') {
                            //System.out.println("Opcode: " +
                            opcodemap.get(instruction[0]);

                            for (int i = 0; i < 6; i++) //opcode
                            {
                                mips[i] = (opcodemap.get(instruction[0])).charAt(i);
// It will put the binary number of the opcode to the mips array
                                //System.out.print(mips[i]);
                            }
                            if (functionName.equals("move")) {
                                rs = instruction[1];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);
                                //System.out.println("rsi: " + rsi);
                                rt = instruction[2];
                                //System.out.println("rt: " + rt);
                                rti = registermap.get(rt);
                                //System.out.println("rti: " + rti);

                                for (int i = 6; i < 11; i++) //rs
                                {
                                    mips[i] = rsi.charAt(i - 6);
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 16; i < 21; i++) //rt
                                {
                                    mips[i] = rti.charAt(i - 16);
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 11; i < 16; i++) //rd
                                {
                                    mips[i] = '0';
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 21; i < 26; i++) // shampt
                                {
                                    mips[i] = '0';
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 26; i < 32; i++) {
                                    mips[i] = rfunctionmap.get("add").charAt(i - 26);
                                    //System.out.print(mips[i]);
                                }
                            } else if (functionName.equals("jr")) {
                                rs = instruction[1];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);

                                //System.out.println("rsi: " + rsi);
                                for (int i = 6; i < 11; i++) //rs
                                {
                                    mips[i] = rsi.charAt(i - 6);
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 11; i < 16; i++) //rd
                                {
                                    mips[i] = '0';
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 16; i < 26; i++) //rd
                                {
                                    mips[i] = '0';
                                    //System.out.print(mips[i]);
                                }
                                for (int i = 26; i < 32; i++) {
                                    mips[i] = rfunctionmap.get("jr").charAt(i - 26);
                                    //System.out.print(mips[i]);
                                }

                            } else if (instruction[0].equals("add")) {

                                rs = instruction[2];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);
                                //System.out.println("rsi: " + rsi);
                                for (int i = 6; i < 11; i++) //rs
                                {
                                    mips[i] = rsi.charAt(i - 6);
                                }
                                rt = instruction[3];
                                //System.out.println("rt: " + rt);
                                rti = registermap.get(rt);
                                //System.out.println("rti: " + rti);
                                rd = instruction[1];
                                //System.out.println("rd: " + rd);
                                rdi = registermap.get(rd);
                                //System.out.println("rdi: " + rdi);
                                for (int i = 11; i < 16; i++) //rt
                                {
                                    mips[i] = rti.charAt(i - 11);
                                }
                                for (int i = 16; i < 21; i++) //rd
                                {
                                    mips[i] = rdi.charAt(i - 16);
                                }
                                for (int i = 21; i < 26; i++) //shamt
                                {
                                    mips[i] = '0';
                                }
                                for (int i = 26; i < 32; i++) //func
                                {
                                    mips[i] =
                                            rfunctionmap.get(instruction[0]).charAt(i - 26);
                                }
                            } else if (instruction[0].equals("sll")) {
                                rd = instruction[1];
                                //System.out.println("rd: " + rd);
                                rdi = registermap.get(rd);
                                //System.out.println("rdi " + rdi);
                                for (int i = 16; i < 21; i++) {
                                    mips[i] = rdi.charAt(i - 16);
                                }
                                rt = instruction[2];
                                //System.out.println("rt " + rt);
                                rti = registermap.get(rt);
                                //System.out.println("rti " + rti);
                                for (int i = 11; i < 16; i++) {
                                    mips[i] = rti.charAt(i - 11);
                                }
                                shamt = instruction[3];
                                //System.out.println("shamt: " + shamt);
                                int shamptinteger = Integer.parseInt(shamt);
                                String shamptbinary =
                                        Integer.toBinaryString(shamptinteger);
                                //System.out.println("shamptbinary:" + shamptbinary);
                                int lengthshamptbinary = shamptbinary.length();
                                int result = 5 - lengthshamptbinary;

                                char[] arrayshampt = new char[5];
                                for (int i = 0; i < result; i++) {
                                    arrayshampt[i] = '0';
                                }

                                for (int i = result; i < 5; i++) {
                                    arrayshampt[i] = shamptbinary.charAt(i - result);
                                }
                                //System.out.println("shampt array:" +
                                Arrays.toString(arrayshampt);
                                String shamptforprint = new String(arrayshampt);
                                for (int i = 21; i < 26; i++) {
                                    mips[i] = shamptforprint.charAt(i - 21);
                                }
                                for (int i = 26; i < 32; i++) {
                                    mips[i] = '0';
                                }
                                for (int i = 6; i < 11; i++) {
                                    mips[i] = '0';
                                }


                            } else {
                                rs = instruction[2];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);
                                //System.out.println("rsi: " + rsi);
                                for (int i = 6; i < 11; i++) {
                                    mips[i] = rsi.charAt(i - 6);
                                }
                                rt = instruction[3];
                                //System.out.println("rt " + rt);
                                rti = registermap.get(rt);
                                //System.out.println(rti);
                                for (int i = 11; i < 16; i++) {
                                    mips[i] = rti.charAt(i - 11);
                                }
                                rd = instruction[1];
                                //System.out.println("rd: " + rd);
                                rdi = registermap.get(rd);
                                //  System.out.println("rdi: " + rdi);
                                for (int i = 16; i < 21; i++) {
                                    mips[i] = rdi.charAt(i - 16);
                                }
                                for (int i = 21; i < 26; i++) {
                                    mips[i] = '0';
                                }
                                for (int i = 26; i < 32; i++) {
                                    mips[i] = rfunctionmap.get("slt").charAt(i - 26);
                                }
                            }

                        } else if (type == 'i') {
                            //System.out.println("Opcode: " +
                            opcodemap.get(instruction[0]);

                            for (int i = 0; i < 6; i++) //opcode
                            {
                                mips[i] = (opcodemap.get(instruction[0])).charAt(i);
// It will put the binary number of the opcode to the mips array
                                //System.out.print(mips[i]);
                            }
                            if (instruction[0].equals("sw") ||
                                    instruction[0].equals("lw")) {
                                String a = instruction[2];
                                String[] arr = a.split("\\(");
                                String str = a;
                                String answer = str.substring(str.indexOf("(") + 1,
                                        str.indexOf(")"));
                                arr[1] = answer;

                                //System.out.println("answer: " + Arrays.toString(arr));
                                rt = instruction[1];
                                //System.out.println("rt: " + rt);
                                rti = registermap.get(rt);
                                //System.out.println("rti: " + rti);
                                for (int i = 11; i < 16; i++) {
                                    mips[i] = rti.charAt(i - 11);
                                }
                                rs = arr[1];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);
                                //System.out.println("rsi: " + rsi);
                                for (int i = 6; i < 11; i++) {
                                    mips[i] = rsi.charAt(i - 6);
                                }
                                offset = arr[0];

                                //System.out.println("offset: " + offset);
                                offseti = Integer.parseInt(offset);
                                if(offseti<0) {
                                    System.out.println("Invalid offset!");
                                    return;

                                }
                                // System.out.println("offseti: " + offseti);
                                String immediatebinary = Integer.toBinaryString(offseti);
                                // System.out.println("immediate binary: " +immediatebinary);
                                int lengthoffset = immediatebinary.length();
                                //System.out.println("length of offset in binary: " +lengthoffset);
                                int res = 16 - lengthoffset;
                                // System.out.println("result: " + res);

                                char[] arrayim = new char[16];
                                for (int i = 0; i < res; i++) {
                                    arrayim[i] = '0';
                                }

                                for (int i = res; i < 16; i++) {
                                    arrayim[i] = immediatebinary.charAt(i - res);
                                }
                                //System.out.println("immediate array: " +
                                Arrays.toString(arrayim);
                                String immediateforprint = new String(arrayim);
                                for (int i = 16; i < 32; i++) {
                                    mips[i] = immediateforprint.charAt(i - 16);
                                }

                                //System.out.println("Last array: " +
                                Arrays.toString(arr);
                            } else if (instruction[0].equals("addi") || instruction[0].equals("slti")) {
                                rs = instruction[2];
                                //System.out.println("rs: " + rs);
                                rsi = registermap.get(rs);
                                //System.out.println("rsi: " + rsi);
                                for (int i = 6; i < 11; i++) {
                                    mips[i] = rsi.charAt(i - 6);
                                }
                                rt = instruction[1];
                                //System.out.println("rt: " + rt);
                                rti = registermap.get(rt);
                                //System.out.println("rti: " + rti);
                                for (int i = 11; i < 16; i++) {
                                    mips[i] = rti.charAt(i - 11);
                                }
                                immediate = instruction[3];
                                //System.out.println("immediate: " + immediate);
                                int immediateinteger = Integer.parseInt(immediate);
                                if(immediateinteger<0){
                                    String binaryimmediate=Integer.toBinaryString(immediateinteger);
                                    char[] arrayimmediate = new char[16];
                                    for(int i=0;i<16;i++){
                                        arrayimmediate[i]=binaryimmediate.charAt(i+16);

                                    }

                                    //System.out.println("arrayimmediate: "+Arrays.toString(arrayimmediate));



                                    String immediateforprint = new String(arrayimmediate);
                                    for (int i = 16; i < 32; i++) {
                                        mips[i] = immediateforprint.charAt(i - 16);
                                    }

                                }
                                else{
                                    //System.out.println("immediateinteger:"+immediateinteger);
                                    String immediatebinary = Integer.toBinaryString(immediateinteger);

                                    //System.out.println("immediatebinary:" + immediatebinary);
                                    int lengthimmediatebinary = immediatebinary.length();
                                    //  System.out.println("immediate binary length: " + lengthimmediatebinary);
                                    int resultimmediate = 16 - lengthimmediatebinary;
                                    //System.out.println("resultimmadiate: " + resultimmediate);

                                    char[] arrayimmediate = new char[16];
                                    for (int i = 0; i < resultimmediate; i++) { arrayimmediate[i] = '0';
                                    }

                                    for (int i = resultimmediate; i < 16; i++) {
                                        arrayimmediate[i] = immediatebinary.charAt(i - resultimmediate);
                                    }
                                    //System.out.println("immediate array:" +Arrays.toString(arrayimmediate);
                                    String immediateforprint = new String(arrayimmediate);
                                    for (int i = 16; i < 32; i++) {
                                        mips[i] = immediateforprint.charAt(i - 16);
                                    }}

                            } else if (type == 'j') {
                                address = instruction[1];
                                //System.out.println("address: " + address);
                            }

                            functionName = null;
                            rt = null;
                            rd = null;
                            rs = null;
                            immediate = null;
                            shamt = null;
                            funct = null;
                            address = null;
                            offset = null;

                        }
                        // System.out.println("MIPS Instruction: " +Arrays.toString(mips));
                        String mipss = new String(mips);
                        //System.out.println(mipss);

                        long mipsint = Long.parseLong(mipss, 2);

                        String hexMips = Long.toHexString(mipsint);
                        int len = hexMips.length();
                        int counter = 8 - len;
                        for (int i = 0; i < counter; i++) {
                            hexMips = "0" + hexMips;
                        }

                        System.out.println("Hex: 0x" + hexMips.toUpperCase());
                        k++;
                    }
            }

        }
            else if (selection == 2)
            {
                FileWriter fwOb = null;
                try {
                    fwOb = new FileWriter("output.obj", false);
                    PrintWriter pwOb = new PrintWriter(fwOb, false);
                    pwOb.flush();
                    pwOb.close();
                    fwOb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String path = "";
                System.out.println("\nEnter the name of file on your computer with its extension (e.g .src/.txt): ");
                path = scanner.next();

                String inst = null;
                try {
                    File myObj = new File(path);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        //System.out.println(data);
                        inst = data;

                        String[] instruction = inst.split(" "); // split method to separate every string according to space

                        // System.out.println("output string: " +
                        Arrays.toString(instruction);

                        while (!(opcodemap.containsKey(instruction[0]))) {
                            System.out.println("You entered invalid entry!");
                            System.out.println("Enter instruction: ");
                            inst = scanner.nextLine();
                            instruction = inst.split(" ");
                        }
                        if(instruction[0].equals("Label:")){
                            functionName=instruction[1];
                            // System.out.println("function name: "+functionName);
                        }
                        else{
                            functionName = instruction[0];
                            //System.out.println("function name: " + functionName);}

                            type = typemap.get(functionName);
                            //System.out.println("type: " + type);

                            if (type == 'r') {
                                //System.out.println("Opcode: " +
                                opcodemap.get(instruction[0]);

                                for (int i = 0; i < 6; i++) //opcode
                                {
                                    mips[i] = (opcodemap.get(instruction[0])).charAt(i);
// It will put the binary number of the opcode to the mips array
                                    //System.out.print(mips[i]);
                                }
                                if (functionName.equals("move")) {
                                    rs = instruction[1];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);
                                    //System.out.println("rsi: " + rsi);
                                    rt = instruction[2];
                                    //System.out.println("rt: " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println("rti: " + rti);

                                    for (int i = 6; i < 11; i++) //rs
                                    {
                                        mips[i] = rsi.charAt(i - 6);
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 16; i < 21; i++) //rt
                                    {
                                        mips[i] = rti.charAt(i - 16);
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 11; i < 16; i++) //rd
                                    {
                                        mips[i] = '0';
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 21; i < 26; i++) // shampt
                                    {
                                        mips[i] = '0';
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 26; i < 32; i++) {
                                        mips[i] = rfunctionmap.get("add").charAt(i - 26);
                                        //System.out.print(mips[i]);
                                    }
                                } else if (functionName.equals("jr")) {
                                    rs = instruction[1];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);

                                    //System.out.println("rsi: " + rsi);
                                    for (int i = 6; i < 11; i++) //rs
                                    {
                                        mips[i] = rsi.charAt(i - 6);
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 11; i < 16; i++) //rd
                                    {
                                        mips[i] = '0';
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 16; i < 26; i++) //rd
                                    {
                                        mips[i] = '0';
                                        //System.out.print(mips[i]);
                                    }
                                    for (int i = 26; i < 32; i++) {
                                        mips[i] = rfunctionmap.get("jr").charAt(i - 26);
                                        //System.out.print(mips[i]);
                                    }

                                } else if (instruction[0].equals("add")) {

                                    rs = instruction[2];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);
                                    //System.out.println("rsi: " + rsi);
                                    for (int i = 6; i < 11; i++) //rs
                                    {
                                        mips[i] = rsi.charAt(i - 6);
                                    }
                                    rt = instruction[3];
                                    //System.out.println("rt: " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println("rti: " + rti);
                                    rd = instruction[1];
                                    //System.out.println("rd: " + rd);
                                    rdi = registermap.get(rd);
                                    //System.out.println("rdi: " + rdi);
                                    for (int i = 11; i < 16; i++) //rt
                                    {
                                        mips[i] = rti.charAt(i - 11);
                                    }
                                    for (int i = 16; i < 21; i++) //rd
                                    {
                                        mips[i] = rdi.charAt(i - 16);
                                    }
                                    for (int i = 21; i < 26; i++) //shamt
                                    {
                                        mips[i] = '0';
                                    }
                                    for (int i = 26; i < 32; i++) //func
                                    {
                                        mips[i] =
                                                rfunctionmap.get(instruction[0]).charAt(i - 26);
                                    }
                                } else if (instruction[0].equals("sll")) {
                                    rd = instruction[1];
                                    //System.out.println("rd: " + rd);
                                    rdi = registermap.get(rd);
                                    //System.out.println("rdi " + rdi);
                                    for (int i = 16; i < 21; i++) {
                                        mips[i] = rdi.charAt(i - 16);
                                    }
                                    rt = instruction[2];
                                    //System.out.println("rt " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println("rti " + rti);
                                    for (int i = 11; i < 16; i++) {
                                        mips[i] = rti.charAt(i - 11);
                                    }
                                    shamt = instruction[3];
                                    //System.out.println("shamt: " + shamt);
                                    int shamptinteger = Integer.parseInt(shamt);
                                    String shamptbinary =
                                            Integer.toBinaryString(shamptinteger);
                                    //System.out.println("shamptbinary:" + shamptbinary);
                                    int lengthshamptbinary = shamptbinary.length();
                                    int result = 5 - lengthshamptbinary;

                                    char[] arrayshampt = new char[5];
                                    for (int i = 0; i < result; i++) {
                                        arrayshampt[i] = '0';
                                    }

                                    for (int i = result; i < 5; i++) {
                                        arrayshampt[i] = shamptbinary.charAt(i - result);
                                    }
                                    //System.out.println("shampt array:" +
                                    Arrays.toString(arrayshampt);
                                    String shamptforprint = new String(arrayshampt);
                                    for (int i = 21; i < 26; i++) {
                                        mips[i] = shamptforprint.charAt(i - 21);
                                    }
                                    for (int i = 26; i < 32; i++) {
                                        mips[i] = '0';
                                    }
                                    for (int i = 6; i < 11; i++) {
                                        mips[i] = '0';
                                    }


                                } else {
                                    rs = instruction[2];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);
                                    //System.out.println("rsi: " + rsi);
                                    for (int i = 6; i < 11; i++) {
                                        mips[i] = rsi.charAt(i - 6);
                                    }
                                    rt = instruction[3];
                                    //System.out.println("rt " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println(rti);
                                    for (int i = 11; i < 16; i++) {
                                        mips[i] = rti.charAt(i - 11);
                                    }
                                    rd = instruction[1];
                                    //System.out.println("rd: " + rd);
                                    rdi = registermap.get(rd);
                                    //  System.out.println("rdi: " + rdi);
                                    for (int i = 16; i < 21; i++) {
                                        mips[i] = rdi.charAt(i - 16);
                                    }
                                    for (int i = 21; i < 26; i++) {
                                        mips[i] = '0';
                                    }
                                    for (int i = 26; i < 32; i++) {
                                        mips[i] = rfunctionmap.get("slt").charAt(i - 26);
                                    }
                                }

                            } else if (type == 'i') {
                                //System.out.println("Opcode: " +
                                opcodemap.get(instruction[0]);

                                for (int i = 0; i < 6; i++) //opcode
                                {
                                    mips[i] = (opcodemap.get(instruction[0])).charAt(i);
// It will put the binary number of the opcode to the mips array
                                    //System.out.print(mips[i]);
                                }
                                if (instruction[0].equals("sw") ||
                                        instruction[0].equals("lw")) {
                                    String a = instruction[2];
                                    String[] arr = a.split("\\(");
                                    String str = a;
                                    String answer = str.substring(str.indexOf("(") + 1,
                                            str.indexOf(")"));
                                    arr[1] = answer;

                                    //System.out.println("answer: " + Arrays.toString(arr));
                                    rt = instruction[1];
                                    //System.out.println("rt: " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println("rti: " + rti);
                                    for (int i = 11; i < 16; i++) {
                                        mips[i] = rti.charAt(i - 11);
                                    }
                                    rs = arr[1];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);
                                    //System.out.println("rsi: " + rsi);
                                    for (int i = 6; i < 11; i++) {
                                        mips[i] = rsi.charAt(i - 6);
                                    }
                                    offset = arr[0];

                                    //System.out.println("offset: " + offset);
                                    offseti = Integer.parseInt(offset);
                                    if(offseti<0) {
                                        System.out.println("Invalid offset!");
                                        return;

                                    }
                                    // System.out.println("offseti: " + offseti);
                                    String immediatebinary = Integer.toBinaryString(offseti);
                                    // System.out.println("immediate binary: " +immediatebinary);
                                    int lengthoffset = immediatebinary.length();
                                    //System.out.println("length of offset in binary: " +lengthoffset);
                                    int res = 16 - lengthoffset;
                                    // System.out.println("result: " + res);

                                    char[] arrayim = new char[16];
                                    for (int i = 0; i < res; i++) {
                                        arrayim[i] = '0';
                                    }

                                    for (int i = res; i < 16; i++) {
                                        arrayim[i] = immediatebinary.charAt(i - res);
                                    }
                                    //System.out.println("immediate array: " +
                                    Arrays.toString(arrayim);
                                    String immediateforprint = new String(arrayim);
                                    for (int i = 16; i < 32; i++) {
                                        mips[i] = immediateforprint.charAt(i - 16);
                                    }

                                    //System.out.println("Last array: " +
                                    Arrays.toString(arr);
                                } else if (instruction[0].equals("addi") || instruction[0].equals("slti")) {
                                    rs = instruction[2];
                                    //System.out.println("rs: " + rs);
                                    rsi = registermap.get(rs);
                                    //System.out.println("rsi: " + rsi);
                                    for (int i = 6; i < 11; i++) {
                                        mips[i] = rsi.charAt(i - 6);
                                    }
                                    rt = instruction[1];
                                    //System.out.println("rt: " + rt);
                                    rti = registermap.get(rt);
                                    //System.out.println("rti: " + rti);
                                    for (int i = 11; i < 16; i++) {
                                        mips[i] = rti.charAt(i - 11);
                                    }
                                    immediate = instruction[3];
                                    //System.out.println("immediate: " + immediate);
                                    int immediateinteger = Integer.parseInt(immediate);
                                    if(immediateinteger<0){
                                        String binaryimmediate=Integer.toBinaryString(immediateinteger);
                                        char[] arrayimmediate = new char[16];
                                        for(int i=0;i<16;i++){
                                            arrayimmediate[i]=binaryimmediate.charAt(i+16);

                                        }

                                        //System.out.println("arrayimmediate: "+Arrays.toString(arrayimmediate));



                                        String immediateforprint = new String(arrayimmediate);
                                        for (int i = 16; i < 32; i++) {
                                            mips[i] = immediateforprint.charAt(i - 16);
                                        }

                                    }
                                    else{
                                        //System.out.println("immediateinteger:"+immediateinteger);
                                        String immediatebinary = Integer.toBinaryString(immediateinteger);

                                        //System.out.println("immediatebinary:" + immediatebinary);
                                        int lengthimmediatebinary = immediatebinary.length();
                                        //  System.out.println("immediate binary length: " + lengthimmediatebinary);
                                        int resultimmediate = 16 - lengthimmediatebinary;
                                        //System.out.println("resultimmadiate: " + resultimmediate);

                                        char[] arrayimmediate = new char[16];
                                        for (int i = 0; i < resultimmediate; i++) { arrayimmediate[i] = '0';
                                        }

                                        for (int i = resultimmediate; i < 16; i++) {
                                            arrayimmediate[i] = immediatebinary.charAt(i - resultimmediate);
                                        }
                                        //System.out.println("immediate array:" +Arrays.toString(arrayimmediate);
                                        String immediateforprint = new String(arrayimmediate);
                                        for (int i = 16; i < 32; i++) {
                                            mips[i] = immediateforprint.charAt(i - 16);
                                        }}

                                } else if (type == 'j') {
                                    address = instruction[1];
                                    //System.out.println("address: " + address);
                                }

                                functionName = null;
                                rt = null;
                                rd = null;
                                rs = null;
                                immediate = null;
                                shamt = null;
                                funct = null;
                                address = null;
                                offset = null;

                            }
                            // System.out.println("MIPS Instruction: " +Arrays.toString(mips));
                            String mipss = new String(mips);
                            //System.out.println(mipss);

                            long mipsint = Long.parseLong(mipss, 2);

                            String hexMips = Long.toHexString(mipsint);
                            int len = hexMips.length();
                            int counter = 8 - len;
                            for (int i = 0; i < counter; i++) {
                                hexMips = "0" + hexMips;
                            }

                            try {
                                FileWriter myWriter = new FileWriter("output.obj",true);
                                String str = "0x" + hexMips;
                                myWriter.write(str.toUpperCase() + "\n");
                                myWriter.close();
                                //System.out.println("Successfully wrote to the file.");
                            } catch (IOException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                            //System.out.println("Hex: 0x" + hexMips.toUpperCase());
                        }
                    }
                    System.out.println("\nSuccessfully wrote to the file.");
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Program Exit");
            }

        }
    }
}