package com.insightfullogic.java8.examples.chapter5;

public class StringCombiner {

    private final String prefix;
    private final String suffix;
    private final String delim;
    private final StringBuilder buIlder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
        this.buIlder = new StringBuilder();
    }

    // 例5-21　add 方法返回连接新元素后的结果
    // BEGIN add
    public StringCombiner add (String word) {
        if(!this.areAtStart()) {
            this.buIlder.append(delim);
        }
        this.buIlder.append(word);

        return this;
    }
    // END add

    // 例5-22　merge 方法连接两个StringCombiner 对象
    // BEGIN merge
    public StringCombiner merge (StringCombiner other) {
        if(!other.equals(this)) {
            if(!other.areAtStart() && !this.areAtStart()){
                other.buIlder.insert(0, this.delim);
            }
            this.buIlder.append(other.buIlder);
        }
        return this;
    }
    // END merge

    // BEGIN toString
    @Override
    public String toString() {
        return prefix + buIlder.toString() + suffix;
    }
    // END toString

    // BEGIN areAtStart
    private boolean areAtStart() {
        return buIlder.length() == 0;
    }
    // END areAtStart
}
