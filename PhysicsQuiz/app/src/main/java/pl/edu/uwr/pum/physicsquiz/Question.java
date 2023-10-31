// Package declaration
package pl.edu.uwr.pum.physicsquiz;

// Importing required classes and packages
import android.os.Parcel;
import android.os.Parcelable;

// Declaring Question class that implements Parcelable interface
public class Question implements Parcelable {
    // Declaring variables for question attributes
    private String mTextResId; // String to store the question text
    private String[] mAnswers; // Array to store the answer options
    private int mCorrectAnswerIndex; // Integer to store the index of the correct answer in the answers array
    private boolean mAnswered; // Boolean to store whether the question has been answered or not

    // Constructor to initialize question attributes
    public Question(String TextResId, String[] Answers, int CorrectAnswerIndex, boolean Answered) {
        mTextResId = TextResId;
        mAnswers = Answers;
        mCorrectAnswerIndex = CorrectAnswerIndex;
        mAnswered = Answered;
    }

    // Constructor to create question from parcel
    protected Question(Parcel in) {
        mTextResId = in.readString();
        mAnswers = in.createStringArray();
        mCorrectAnswerIndex = in.readInt();
        mAnswered = in.readByte() != 0;
    }

    // Override writeToParcel method to write question attributes to parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTextResId);
        dest.writeStringArray(mAnswers);
        dest.writeInt(mCorrectAnswerIndex);
        dest.writeByte((byte) (mAnswered ? 1 : 0));
    }

    // Override describeContents method
    @Override
    public int describeContents() {
        return 0;
    }

    // Creator object to create question from parcel
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    // Getter and setter methods for question attributes
    public String getTextResId() {
        return mTextResId;
    }

    public void setTextResId(String textResId) {
        this.mTextResId = textResId;
    }

    public String[] getAnswers() {
        return mAnswers;
    }

    public void setAnswers(String[] answers) {
        this.mAnswers = answers;
    }

    public int getCorrectAnswerIndex() {
        return mCorrectAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.mCorrectAnswerIndex = correctAnswerIndex;
    }

    public boolean isAnswered() {
        return mAnswered;
    }

    public void setAnswered(boolean answered) {
        this.mAnswered = answered;
    }
}
