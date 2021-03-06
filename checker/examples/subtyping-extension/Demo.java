import java.util.*;

import qual.Encrypted;
import qual.PossiblyUnencrypted;

abstract class EncryptionDemo {

    public @Encrypted String encrypt(String text) {
        byte[] b = text.getBytes();
        for (int i = 0; i < b.length; b[i++]++);
        //:: warning: (cast.unsafe)
        return (@Encrypted String)new String(b);
    }

    // Only send encrypted data!
    abstract void sendOverTheInternet(@Encrypted String msg);

    void sendText() {
        @Encrypted String s = encrypt("foo");   // valid
        sendOverTheInternet(s);                 // valid

        String t = encrypt("bar");              // valid (subtype)
        sendOverTheInternet(t);                 // valid (flow)

        List<@Encrypted String> lst = new LinkedList<@Encrypted String>();
        lst.add(s);
        lst.add(t);

        for (String str : lst)                  // valid
            sendOverTheInternet(str);
    }

    void sendPassword() {
        String password = "unencrypted";
        sendOverTheInternet(password);          // invalid
    }

}
