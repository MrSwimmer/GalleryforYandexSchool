package com.mrswimmer.galleryforyandexschool.data.settings;

public interface Settings {
    String coffeKinds[] = {"Арабика", "Робуста", "Либерика", "Эксцельза"};
    String teaKinds[] = {"Черный", "Зеленый", "Белый", "Желтый", "Улун", "Пуэр"};
    String cities[] = {"Новосибирск", "Кемерово"};
    boolean checked[] = {true, true, true, true, true, true};
    String SORTED_SET = "sorted set";
    String SORT = "sort";
    String USER_ID = "user id";
    String USERNAME = "username";
    int GALLERY_REQUEST = 0;
    String GALLERY_GALLERY = "gallery";
    String GALLERY_MY_GALLERY = "my gallery";
    String GALLERY_FAVORITE = "favorite";
    String GALLERY_BUNDLE_KEY = "gallery bundle key";
    String DETAIL_BUNDLE_ID = "detail bundle id";
}
