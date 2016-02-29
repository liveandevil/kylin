package org.apache.kylin.storage.hbase.ii.coprocessor.endpoint;

import org.apache.kylin.common.util.Dictionary;
import org.apache.kylin.dict.IDictionaryAware;
import org.apache.kylin.invertedindex.index.TableRecordInfoDigest;
import org.apache.kylin.metadata.model.TblColRef;
import org.apache.kylin.storage.hbase.common.coprocessor.CoprocessorRowType;

/**
 */
public class LocalDictionary implements IDictionaryAware {

    private CoprocessorRowType type;
    private Dictionary<?>[] colDictMap;
    private TableRecordInfoDigest recordInfo;

    public LocalDictionary(Dictionary<?>[] colDictMap, CoprocessorRowType type, TableRecordInfoDigest recordInfo) {
        this.colDictMap = colDictMap;
        this.type = type;
        this.recordInfo = recordInfo;
    }

    @Override
    public int getColumnLength(TblColRef col) {
        return recordInfo.length(type.getColIndexByTblColRef(col));
    }

    @Override
    public Dictionary<?> getDictionary(TblColRef col) {
        return this.colDictMap[type.getColIndexByTblColRef(col)];
    }
}