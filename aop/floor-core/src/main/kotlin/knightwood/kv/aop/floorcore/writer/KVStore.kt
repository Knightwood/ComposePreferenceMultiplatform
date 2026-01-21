package knightwood.kv.aop.floorcore.writer

abstract class KVStore<DATA_BASE> {
    abstract fun provideDefaultDb(db: DATA_BASE)
}
