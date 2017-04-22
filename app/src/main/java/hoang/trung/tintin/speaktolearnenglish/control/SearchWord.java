package hoang.trung.tintin.speaktolearnenglish.control;

import android.util.Log;

import hoang.trung.tintin.speaktolearnenglish.common.Common;
import hoang.trung.tintin.speaktolearnenglish.model.SqlLiteHelper;
import hoang.trung.tintin.speaktolearnenglish.view.IMainPage;
import hoang.trung.tintin.speaktolearnenglish.view.MainPage;

/**
 * Created by Tintin on 4/20/2017.
 */

public class SearchWord {
    private final SqlLiteHelper mSqlLiteHelper;
    IMainPage mIMainPageCallback;
    public SearchWord(IMainPage iMainPageCallback){
        mIMainPageCallback = iMainPageCallback;
        mSqlLiteHelper = new SqlLiteHelper(((MainPage)mIMainPageCallback));
        mSqlLiteHelper.addWord("technology", "the practical application of knowledge especially in a particular area");
        mSqlLiteHelper.addWord("hello", "an expression or gesture of greeting");
        mSqlLiteHelper.addWord("intelligence", "the ability to learn or understand or to deal with new or trying situations");
    }
    public void search(String word){
        Log.d(Common.TAG, "search:" + word);
        String meaning = mSqlLiteHelper.readWord(word.toLowerCase());
        Log.d(Common.TAG, "meaning: " + meaning);
        mIMainPageCallback.updateSearchResult(word, meaning);
    }
}
