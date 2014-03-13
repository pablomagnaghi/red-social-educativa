package com.material

import java.util.Date;

class Archivo {
    String filename
    byte[] filedata // long blob
    Date uploadDate = new Date()

    static constraints = {
        filename blank:false
        filedata blank: false, maxSize:33554432 //32MB de tamaño
    }
}
