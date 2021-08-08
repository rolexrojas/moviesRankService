package com.backbase.moviesRankService.types;

public enum MovieCategory {
    BEST_PICTURE_CATEGORY("Best Picture");

    private String category;

    MovieCategory(String myCategory){
        this.category = myCategory;
    }

    public String getCategory(){
        return category;
    }
}
