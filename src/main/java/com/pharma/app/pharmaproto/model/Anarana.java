package com.pharma.app.pharmaproto.model;


public class Anarana {
    private  int id;
    private  String anarana;
    private  String fanampiny;
    private int taona;

    public int getId() {
        return id;
    }

    public String getAnarana() {
        return anarana;
    }

    public String getFanampiny() {
        return fanampiny;
    }

    public int getTaona() {
        return taona;
    }
//
//    public Anarana(AnaranaBuilder anaranaBuilder) {
//        this.id = anaranaBuilder.id;
//        this.anarana = anaranaBuilder.anarana;
//        this.fanampiny = anaranaBuilder.fanampiny;
//        this.taona = anaranaBuilder.taona;
//    }

//    //Builder Setters
//    public static class AnaranaBuilder {
//        private int id;
//        private String anarana;
//        private String fanampiny;
//        private int taona;
//
//        public AnaranaBuilder(String anarana, String fanampiny) {
//            this.anarana = anarana;
//            this.fanampiny = fanampiny;
//        }
//
//        public AnaranaBuilder id(int id) {
//            this.id = id;
//            return this;
//        }
//
//        public AnaranaBuilder taona(int taona) {
//            this.taona = taona;
//            return this;
//        }
//
//        public Anarana build() {
//            return new Anarana(this);
//        }
//    }
}
