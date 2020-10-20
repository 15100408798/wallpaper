package com.yushang.wallpaper.common.config.Shiro.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializeUtils  {

    /**
     * 序列化
     */
    public static byte[] serialize(Object object){
        //先要创建某些OutputStream对象，然后将其封装在一个ObjectOutputStream对象内，再调用writeObject()方法即可序列化一个对象；反序列化也类似。
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try{
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 反序列化
     */
    public static Object unserialize(byte[] bytes){
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
