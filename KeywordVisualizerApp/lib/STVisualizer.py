import streamlit as st
import matplotlib.pyplot as plt
from collections import Counter
from wordcloud import WordCloud

# 수평 막대그래프 그리기
def showPlot(test_list: Counter , counter):
    fig, ax = plt.subplots()
    
    word_list = [word for word, count in test_list.most_common(counter)]
    count_list = [count for word, count in test_list.most_common(counter)]
    
    ax.barh(word_list[::-1], count_list[::-1])
    ax.set_title("대한민국 헌법 키워드 분석")
    ax.set_xlabel("빈도수")
    ax.set_ylabel("키워드")

    st.pyplot(fig)  # Streamlit에 출력

# 워드클라우드 그리기
def showWordCloud(test_list: Counter , counter):
    font_path = "c:/Windows/fonts/malgun.ttf"  # 한글 폰트 경로

    word_list = [word for word, count in test_list.most_common(counter)]
    count_list = [count for word, count in test_list.most_common(counter)]
    
    word_count_dict = dict(zip(word_list, count_list))
    
    wordcloud = WordCloud(
        font_path=font_path,
        width=600,
        height=400,
        max_words=50,
        background_color="ivory"
    ).generate_from_frequencies(word_count_dict)  # Counter는 generate_from_frequencies 사용

    fig, ax = plt.subplots()
    ax.imshow(wordcloud, interpolation='bilinear')
    ax.axis('off')

    st.pyplot(fig)  # Streamlit에 출력
