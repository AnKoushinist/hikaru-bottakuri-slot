package jp.tjkapp.adfurikunsdk.moviereward;

interface AdfurikunMovieListener<T extends MovieData> {
    void onAdClose(T t);

    void onFailedPlaying(T t);

    void onFinishedPlaying(T t);

    void onPrepareSuccess();

    void onStartPlaying(T t);
}
