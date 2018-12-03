  
package com.jimingqiang.study.canal.filter;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;

import java.util.List;

public interface MessageFilter {

    public boolean filter(Entry entry);
    
    public void filter(List<Entry> entries);
}
  
