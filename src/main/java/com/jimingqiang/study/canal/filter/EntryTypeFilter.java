package com.jimingqiang.study.canal.filter;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class EntryTypeFilter implements MessageFilter {
    public void filter(List<Entry> entries) {
        if (CollectionUtils.isEmpty(entries)) {
            return;
        }
        Iterator<Entry> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Entry next = iterator.next();
            if (!filter(next)) {
                iterator.remove();
            }
        }

    }

    /**
     * 判定Entry是否需要处理
     */
    public boolean filter(Entry entry) {
        if (entry != null && entry.getEntryType() == EntryType.ROWDATA
                && entry.getHeader().getEventType() != CanalEntry.EventType.QUERY) {//select 也要过滤掉
            return true;
        }
        return false;
    }
}
  
