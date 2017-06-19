#!/usr/bin/env groovy
package common

import java.security.MessageDigest
import java.nio.file.Files


class Utils {

    static def md5(String s, int limit = 0){
        def x = MessageDigest.getInstance("MD5").digest(s.bytes).encodeHex().toString()
        if (limit) {
          return x.substring(0, limit)
        } else
          return x
    }

    static def mkdtemp(String prefix=null) {
        def mydir = Files.createTempDirectory(prefix).toString()
              addShutdownHook {
                  def x = new File(mydir)
                  x.deleteDir()
                  println "cleaned ${mydir}"
              }
        mydir
    }


}
