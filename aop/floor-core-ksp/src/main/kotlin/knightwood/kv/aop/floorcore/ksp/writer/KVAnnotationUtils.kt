package knightwood.kv.aop.floorcore.ksp.writer

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import knightwood.kv.aop.floorcore.ksp.processor.getArgumentValue
import knightwood.kv.aop.floorcore.ksp.processor.isNullable

object KVAnnotationUtils {
    /**
     * 解析注解得到的Dao文件名称可能为空，此时将返回默认Dao名称：使用类名加上_Dao
     *
     * @param kvStoreAnnotation 类上的 @KVStore 注解信息
     * @param classDeclaration 键值对映射数据类
     *
     * @return Dao文件名称
     */
    fun parseDaoName(
        kvStoreAnnotation: KSAnnotation,
        classDeclaration: KSClassDeclaration,
    ): String {
        val daoName = kvStoreAnnotation.getArgumentValue("daoName")
        if (daoName != null && daoName.isNotBlank()) {
            return daoName
        }
        return (classDeclaration.simpleName.asString() + "_Dao")
    }

    /**
     * 解析某@KVField属性，获取key名称
     *
     * //例如：val age: Preferences.Key<Int> = intPreferenceKey("age")
     * //        数据键                                        数据键名
     *
     * @param kvStoreAnnotation propertyDeclaration 标记的 @KVField注解信息
     * @param propertyDeclaration 某个标记了@KVField注解的数据类字段
     * @return 数据键名称
     */
    fun parseKVFieldKeyName(
        annotation: KSAnnotation,
        propertyDeclaration: KSPropertyDeclaration,
    ): String {
        val key = annotation.getArgumentValue("key")
        if (key != null && key.isNotBlank()) {
            return key
        }
        return propertyDeclaration.simpleName.asString()
    }

    /**
     *
     * 解析某@KVField属性，获取默认值
     *
     *  默认值有如下几种格式
     * ```
     * @KVField(defaultValue = "\"uui\"") -> "uui"
     * @KVField(defaultValue = "\"\"") -> ""
     * @KVField(defaultValue = "") -> 没值
     * @KVField(defaultValue = "null") -> null
     * @KVField(defaultValue = "12") -> 12
     *
     * ```
     *
     *
     * 最终赋值有如下几种格式
     * ```
     * this[Keys.isDebug]?:defaultValue
     * this[Keys.isDebug]
     * this[Keys.isDebug]?:null
     * this[Keys.isDebug]?:"defaultValue"
     * this[Keys.isDebug]!!
     *```
     *
     * @param annotation propertyDeclaration 标记的 @KVField注解信息
     * @param propertyDeclaration 某个标记了@KVField注解的数据类字段
     * @return 数据键对应的赋值语句
     */
    fun parseDefaultValueStr(
        annotation: KSAnnotation,
        propertyDeclaration: KSPropertyDeclaration,
    ): String {
        val isNullable = propertyDeclaration.isNullable()
        val tmp0 = annotation.getArgumentValue("defaultValue")

        if (tmp0.isNullOrBlank()) {//获取不到值或者空值，意味着没有默认值
            if (isNullable) {
                return "?:null"
            } else {
                return "!!"
            }
        } else {
            return "?:$tmp0"
        }
    }
}
