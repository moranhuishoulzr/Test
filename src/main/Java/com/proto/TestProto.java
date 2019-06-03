package com.proto;
import com.google.protobuf.InvalidProtocolBufferException;
import com.proto.proto.PersonEntity;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-22 16:30
 */
public class TestProto {
    public static void main(String[] args) {
        PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
        builder.setId(1);
        builder.setName("ant");
        builder.setEmail("9911@qq.com");
        PersonEntity.Person person = builder.build();
        System.out.println("before "+person.toString());

        System.out.println("===========Person Byte==========");
        for (byte b :person.toByteArray()){
            System.out.print(b);
        }

        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        byte[] byteArray =person.toByteArray();
        try {
            PersonEntity.Person p2 = person.parseFrom(byteArray);
            System.out.println("after :" +p2.toString());
        }catch (InvalidProtocolBufferException e){
            e.printStackTrace();
        }
    }
}
