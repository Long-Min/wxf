package com.chenlong.entity.vo;

import java.io.Serializable;
import java.util.List;

public class JqueryTreeView implements Serializable {
    private Integer fid;
    private String text;
    private String href;
    private List<JqueryTreeView> nodes;

    public JqueryTreeView() {
    }

    public JqueryTreeView(Integer fid, String text, String href) {
        this();
        this.fid = fid;
        this.text = text;
        this.href = href;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<JqueryTreeView> getNodes() {
        return nodes;
    }

    public void setNodes(List<JqueryTreeView> nodes) {
        this.nodes = nodes;
    }
}
