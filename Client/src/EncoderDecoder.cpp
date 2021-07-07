//
// Created by spl211 on 07/01/2021.
//

#include <EncoderDecoder.h>
#include <sstream>
#include <iterator>
#include <iostream>
// int len
// vector<char> bytes


std::string EncoderDecoder::decodeLine(std::string &line) {
    return std::string(line.begin(), line.end()-1);
}

std::string EncoderDecoder::encode(std::string &message) { // a msg the client want to send


    std::vector<char> bytesArr(2,0); // init with {0,0}

    std::istringstream iss(message);
    std::vector<std::string> split((std::istream_iterator<std::string>(iss)), // "some string" -> ["some", "string"]
                                   std::istream_iterator<std::string>());
    std::string op = split[0];
    short opCode;
    split.erase(split.begin());

    if (op == "ADMINREG") {
        opCode = 1;
        encodeString(bytesArr, split);
    } else if (op == "STUDENTREG") {
        opCode = 2;
        encodeString(bytesArr, split);
    } else if (op == "LOGIN") {
        opCode = 3;
        encodeString(bytesArr, split);
    } else if (op == "LOGOUT") {
        opCode = 4;
    } else if (op == "COURSEREG") {
        opCode = 5;
        encodeShort(bytesArr,split);
    } else if (op == "KDAMCHECK") {
        opCode = 6;
        encodeShort(bytesArr,split);
    } else if (op == "COURSESTAT") {
        opCode = 7;
        encodeShort(bytesArr,split);
    } else if (op == "STUDENTSTAT") {
        opCode = 8;
        encodeString(bytesArr, split);
    } else if (op == "ISREGISTERED") {
        opCode = 9;
        encodeShort(bytesArr,split);
    } else if (op == "UNREGISTER") {
        opCode = 10;
        encodeShort(bytesArr,split);
    } else if (op == "MYCOURSES") {
        opCode = 11;
    }


    shortToBytes(opCode, bytesArr); // inserting opCode first.

    return std::string(bytesArr.begin(), bytesArr.end()); // converts the vector to string

}

void EncoderDecoder::shortToBytes(short num, std::vector<char> &bytesArr) {
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}

void EncoderDecoder::encodeString(std::vector<char> &bytesArr, std::vector<std::string> &msg) {
    for (std::string s:msg){
        std::copy(s.begin(), s.end(), std::back_inserter(bytesArr)); // if s = "cd" and bytesArr = {'a','b'} it gives: {'a','b','c','d'}
        bytesArr.push_back(0); // pushing null char
    }
}

void EncoderDecoder::encodeShort(std::vector<char> &bytesArr, std::vector<std::string> &msg) {
    short n = std::stoi(msg[0]); // convert string to int (or short)
    bytesArr.push_back(((n >> 8) & 0xFF));
    bytesArr.push_back((n & 0xFF));
}

short EncoderDecoder::bytesToShort(char *bytesArr) {
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}

