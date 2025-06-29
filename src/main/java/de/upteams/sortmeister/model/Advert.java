package de.upteams.sortmeister.model;

    public class Advert {
        private Long id;
        @lombok.Setter
        @lombok.Getter
        private String title;
        @lombok.Setter
        @lombok.Getter
        private String description;
        @lombok.Setter
        @lombok.Getter
        private String photo;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }
