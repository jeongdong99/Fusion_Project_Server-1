package persistence.converter;

import controller.protocol.Protocol;
import persistence.DTO.AdminDTO;

import java.util.List;

public class Converter {

    public static int byteToInt(byte[] data, int pos) {

        int result = ((int) (data[pos] & 0xff) << 8) |
                     ((int) data[pos+1] & 0xff);

        return result;

    }

    public static void writeAdmin(Protocol protocol, List<AdminDTO> dtoList) {

        for(int i=0; i<dtoList.size(); i++) {
            writeAdmin(protocol, dtoList.get(i));
        }
    }

    public static void writeAdmin(Protocol protocol, AdminDTO adminDTO) {

        protocol.addBodyIntData((int) adminDTO.getUserId());
        protocol.addBodyStringData(adminDTO.getAdminId());
        protocol.addBodyStringData(adminDTO.getPassword());
        protocol.addBodyStringData(adminDTO.getName());
        protocol.addBodyStringData(adminDTO.getPhoneNumber());

    }

    












}
