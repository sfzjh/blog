package com.sfzjh.util;


import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * 生成UUID号码
 * @Author 孙飞
 * @Date 2021年03月09日  22:19
 * @PackageName : com.sfzjh.util
 * @Name UUIDUtils
 * @Version 1.0
 * @Description TODO
 */
@NoArgsConstructor
public class UUIDUtils {
   public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "");
   }


}
