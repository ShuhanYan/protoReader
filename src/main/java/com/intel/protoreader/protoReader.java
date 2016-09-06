package com.intel.protoreader;

import com.google.protobuf.*;
import com.intel.protoreader.Caffe;
import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yansh on 16-9-2.
 */
public class protoReader {
    public static void main(String[] args ){
        try{
            File f = new File("bvlc_googlenet.caffemodel");
            InputStream raw_input = new FileInputStream(f);
            CodedInputStream coded_input = CodedInputStream.newInstance(raw_input);

            com.intel.protoreader.Caffe.NetParameter caffe = Caffe.NetParameter.parseFrom(coded_input);
            Map<Descriptors.FieldDescriptor, Object> params = caffe.getAllFields();
            for(Map.Entry<Descriptors.FieldDescriptor,Object> param : params.entrySet()){
                if(param.getKey().isRepeated()){
                    List<Caffe.V1LayerParameter> paramLists = (List<Caffe.V1LayerParameter>)param.getValue();
                    for(Caffe.V1LayerParameter l : paramLists){
                        l.getParamList();
                    }
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
