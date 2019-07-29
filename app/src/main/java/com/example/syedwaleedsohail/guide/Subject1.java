package com.example.syedwaleedsohail.guide;

/**
 * Created by Syed Waleed Sohail on 14/02/2019.
 */

class Subject1 {


    String s_heading;
    String s_explanation;
    String s_example;


    public Subject1(){

    }

    public Subject1(String s_heading, String s_explanation, String s_example) {

        this.s_heading = s_heading;
        this.s_explanation = s_explanation;
        this.s_example = s_example;
    }

    public String getS_heading() {
        return s_heading;
    }

    public String getS_explanation() {
        return s_explanation;
    }

    public String getS_example() {
        return s_example;
    }


    public void setS_heading(String s_heading) {
        this.s_heading = s_heading;
    }

    public void setS_explanation(String s_explanation) {
        this.s_explanation = s_explanation;
    }

    public void setS_example(String s_example) {
        this.s_example = s_example;
    }



}