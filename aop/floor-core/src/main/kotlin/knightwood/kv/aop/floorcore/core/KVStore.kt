package knightwood.kv.aop.floorcore.core

abstract class KVStore<DATA_BASE> {
    abstract fun provideDefaultDb(db: DATA_BASE)
}
