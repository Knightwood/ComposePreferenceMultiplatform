package knightwood.kv.aop.floorcore.core

abstract class KVDB<DATA_BASE> {
    abstract fun provideDefaultDb(db: DATA_BASE)
}
