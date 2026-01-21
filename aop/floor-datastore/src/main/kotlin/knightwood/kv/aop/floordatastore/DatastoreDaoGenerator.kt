package knightwood.kv.aop.floordatastore

import com.google.auto.service.AutoService
import knightwood.kv.aop.floorcore.writer.IDaoGenerator

@AutoService(IDaoGenerator::class)
class DatastoreDaoGenerator : IDaoGenerator{
}
