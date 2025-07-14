/*
 *  Copyright (c) 2023-2025, Agents-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.core.chain;

import com.agentsflex.core.memory.ContextMemory;
import com.agentsflex.core.memory.DefaultContextMemory;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public abstract class ChainNode implements Serializable {

    protected String id;
    protected String name;
    protected String description;

    protected boolean async = false;
    protected List<ChainEdge> inwardEdges;
    protected List<ChainEdge> outwardEdges;

    protected NodeCondition condition;

    protected ContextMemory memory = new DefaultContextMemory();
    protected ChainNodeStatus nodeStatus = ChainNodeStatus.READY;

    // 链路执行相关属性
    protected boolean callbackEnable = false ; // 是否允许回调
    protected NodeCondition callbackBreakCondition;  // 跳出回调条件

    // 循环执行相关属性
    protected boolean loopEnable = false;           // 是否启用循环执行
    protected long loopIntervalMs = 1000;            // 循环间隔时间（毫秒）
    protected NodeCondition loopBreakCondition;      // 跳出循环的条件
    protected int maxLoopCount = 0;                  // 0 表示不限制循环次数

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public void setInwardEdges(List<ChainEdge> inwardEdges) {
        this.inwardEdges = inwardEdges;
    }

    public void setOutwardEdges(List<ChainEdge> outwardEdges) {
        this.outwardEdges = outwardEdges;
    }

    public void setCondition(NodeCondition condition) {
        this.condition = condition;
    }

    public void setMemory(ContextMemory memory) {
        this.memory = memory;
    }

    public void setNodeStatus(ChainNodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public void setNodeStatusFinished() {
        if (this.nodeStatus == ChainNodeStatus.ERROR) {
            this.setNodeStatus(ChainNodeStatus.FINISHED_ABNORMAL);
        } else {
            this.setNodeStatus(ChainNodeStatus.FINISHED_NORMAL);
        }
    }

    protected void addOutwardEdge(ChainEdge edge) {
        if (this.outwardEdges == null) {
            this.outwardEdges = new ArrayList<>();
        }
        this.outwardEdges.add(edge);
    }

    protected void addInwardEdge(ChainEdge edge) {
        if (this.inwardEdges == null) {
            this.inwardEdges = new ArrayList<>();
        }
        this.inwardEdges.add(edge);
    }

    public void setLoopEnable(boolean loopEnable) {
        this.loopEnable = loopEnable;
    }

    public void setLoopIntervalMs(long loopIntervalMs) {
        this.loopIntervalMs = loopIntervalMs;
    }

    public void setLoopBreakCondition(NodeCondition loopBreakCondition) {
        this.loopBreakCondition = loopBreakCondition;
    }

    public void setMaxLoopCount(int maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
    }

    protected abstract Map<String, Object> execute(Chain chain);

    public List<Parameter> getParameters() {
        return null;
    }
}
