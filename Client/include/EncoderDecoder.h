//
// Created by spl211 on 07/01/2021.
//

#include <string>
#include <vector>

#ifndef BOOST_ECHO_CLIENT_ENCODERDECODER_H
#define BOOST_ECHO_CLIENT_ENCODERDECODER_H

#endif //BOOST_ECHO_CLIENT_ENCODERDECODER_H

class EncoderDecoder {
private:

public:

    std::string decodeLine(std::string& Line);

    std::string encode(std::string& message);

    short bytesToShort(char* bytesArr);

    void shortToBytes(short num, std::vector<char>& bytesArr);

    void encodeString(std::vector<char>& v, std::vector<std::string>& msg);

    void encodeShort(std::vector<char>& v, std::vector<std::string>& msg);

};
