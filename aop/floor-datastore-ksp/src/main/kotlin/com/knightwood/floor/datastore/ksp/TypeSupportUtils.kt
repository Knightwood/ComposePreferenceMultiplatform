package knightwood.kv.aop.floordatastore.ksp

import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.ClassName
import com.knightwood.floor.core.ksp.writer.TypeConvertMapper
import com.knightwood.floor.datastore.DataStoreUtils

object TypeSupportUtils {
    /**
     *  判断属性是否原生支持，不需要TypeConvertor转换
     */
    fun isSupport(type: ClassName) = DataStoreUtils.isSupport(type.simpleName)

    /**
     * 如果一个类型不是原生支持的，需要使用TypeConvertor转换，
     * 使用此方法，根据输入参数和返回值获取一个转换语句
     * 例如
     * ```
     * @KVStore
     * data class Bean(
     *      @KVField val name: String,
     *      @KVField val age: Int，
     *      @KVField val uuid:UUID,
     *)
     * ```
     *
     * uuid字段不被原生支持，需要使用TypeConvertor转换
     * ```
     * //写入键值对数据库时，将uuid转换成字符串保存
     * preference["uuid", XXXXUtils.uuid2String(entity.uuid)]
     *
     * //从键值对数据库中读取时，将字符串转换成uuid
     * entity.uuid = XXXXUtils.string2Uuid(preference["uuid"])
     *
     * ```
     *
     */
    fun findFunction(input: ClassName, output: ClassName): KSFunctionDeclaration? {
        return TypeConvertMapper.map[input to output]
    }
}
