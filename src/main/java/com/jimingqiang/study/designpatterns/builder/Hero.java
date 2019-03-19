package com.jimingqiang.study.designpatterns.builder;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 20:06
 * @Description: builder模式
 */
public class Hero {

    private String name;

    private Integer age;

    private Hero(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder{
        private String name;

        private Integer age;


        public Builder(String name) {
            this.name = name;
        }

        public Builder withAge(Integer age){
            this.age = age;

            return this;
        }

        public Hero build(){
            return new Hero(this);
        }


    }
}
