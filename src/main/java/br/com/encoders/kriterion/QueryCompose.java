package br.com.encoders.kriterion;

public final class QueryCompose {
    private boolean select;
    private boolean from;
    private boolean where;
    private boolean join;
    private boolean group;
    private boolean having;
    private boolean order;

    public void composeSelect() {
        this.select = true;
    }
    public boolean isSelectComposed() {
        return this.select;
    }

    public void composeFrom() {
        this.from = true;
    }
    public boolean isFromComposed() {
        return this.from;
    }

    public void composeWhere() {
        this.where = true;
    }
    public boolean isWhereComposed() {
        return this.where;
    }

    public void composeJoin() {
        this.join = true;
    }
    public boolean isJoinComposed() {
        return this.join;
    }

    public void composeGroup() {
        this.group = true;
    }
    public boolean isGroupComposed() {
        return this.group;
    }

    public void composeHaving() {
        this.having = true;
    }
    public boolean isHavingComposed() {
        return this.having;
    }

    public void composeOrder() {
        this.order = true;
    }
    public boolean isOrderComposed() {
        return this.order;
    }
}
