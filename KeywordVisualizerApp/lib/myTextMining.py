# 리스트를 csv 파일로 저장
#filename = f"./data/{keyword}_naver_new.csv"

#데이터로 부터 고빈도 '단어' 수평막대 그래프 , 워드 클라우트로 시각화
# 입력을 데이터 프레임 ? 대상 컬렴명 ? 

#

def counterVector(data_list , column):
    from collections import Counter
    from konlpy.tag import Okt
    okt = Okt()
    okt_list = []
    print(data_list)

    for item_dict in data_list['items']:
        if column in item_dict:
                 value = item_dict[column]
                 word = okt.nouns(value)
                 okt_list.extend(word)

    #빈도수 확인
    count = Counter(okt_list) #('빛나는' , 1 ) 이런식으로 데이터가 들어있을것
    print(okt_list) #빈문자가 나오는데 ? 
    print(count , "*********")
    return count
    
    

def showPlot(test_list):
    import matplotlib.pyplot as plt
    
    word_list = [word for word, count in test_list.most_common(20)]
    count_list = [count for word, count in test_list.most_common(20)]
    
    # 수평 막대그래프
    plt.barh(word_list[::-1] , count_list[::-1])

    # 그래프 정보 추가 
    plt.title("대한민국 헌법 키워드 분석")
    plt.xlabel("빈도수")
    plt.ylabel("키워드")
    # 화면에 출력
    plt.show()
    



def showWordCloud(test_list):
    from wordcloud import WordCloud
    import matplotlib.pyplot as plt
    # 한글 폰트 path 지정
    font_path = "c:/Windows/fonts/malgun.ttf"

    # WordCloud 객체 생성
    wordcloud = WordCloud(font_path,
                        width = 600,
                        height = 400,
                        max_words = 50,
                        background_color="ivory")
    
    
    # 전체 텍스트로 워드클라우드 시각화
    wordcloud = wordcloud.generate(test_list)
    plt.imshow(wordcloud)
    plt.axis('off')
    plt.show()