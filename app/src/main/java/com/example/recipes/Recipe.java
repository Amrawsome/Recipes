package com.example.recipes;

public class Recipe {
        int _recipeID;
        String _recipeName;
        String _recipeIngredients;
        String _recipeInstructions;
        String _recipeLocation;
        int _recipeUIDFK;

        public Recipe(){};

        public Recipe(int recipeID, String recipeName, String recipeIngredients, String recipeInstructions, String recipeLocation,  int recipeUIDFK) {
                this._recipeID = recipeID;
                this._recipeName = recipeName;
                this._recipeIngredients = recipeIngredients;
                this._recipeInstructions = recipeInstructions;
                this._recipeLocation = recipeLocation;
                this._recipeUIDFK = recipeUIDFK;
        }

        public int get_recipeID() {
                return _recipeID;
        }

        public void set_recipeID(int _recipeID) {
                this._recipeID = _recipeID;
        }

        public String get_recipeName() {
                return _recipeName;
        }

        public void set_recipeName(String _recipeName) {
                this._recipeName = _recipeName;
        }

        public String get_recipeIngredients() {
                return _recipeIngredients;
        }

        public void set_recipeIngredients(String _recipeIngredients) {
                this._recipeIngredients = _recipeIngredients;
        }

        public String get_recipeInstructions() {
                return _recipeInstructions;
        }

        public void set_recipeInstructions(String _recipeInstructions) {
                this._recipeInstructions = _recipeInstructions;
        }


        public String get_recipeLocation() {
                return _recipeLocation;
        }

        public void set_recipeLocation(String _recipeLocation) {
                this._recipeLocation = _recipeLocation;
        }

        public int get_recipeUIDFK() {
                return _recipeUIDFK;
        }

        public void set_recipeUIDFK(int _recipeUIDFK) {
                this._recipeUIDFK = _recipeUIDFK;
        }
}

