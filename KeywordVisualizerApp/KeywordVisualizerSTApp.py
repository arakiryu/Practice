import streamlit as st
import myTextMining as mt
import NaverNewsCrawler as nc
import STVisualizer as stv
import numpy as np


#검색
keyword = st.sidebar.text_input("검색 키워드")
if keyword:
    df_list = nc.serchNaverNews(keyword)#json 반환에서 dataFrame 반환으로 수정 
   

#여기까진 실행됨
    
    
#파일 업로드
uploaded_file = st.sidebar.file_uploader("파일을 업로드하세요", type=["csv"])

if uploaded_file is not None:
    st.sidebar.write("파일 이름:", uploaded_file.name)
    st.sidebar.write("파일 타입:", uploaded_file.type)
    st.sidebar.write("파일 크기:", uploaded_file.size)
    
    # CSV 파일 읽기
    import pandas as pd
    file_df = pd.read_csv(uploaded_file)
    st.sidebar.write(f"파일 데이터 행 수: {len(file_df)}")
    
    # 파일 칼럼 선택
    file_column = st.sidebar.text_input("파일 데이터의 분석할 칼럼명", key="file_column")
    
    if file_column:
        if file_column in file_df.columns:
            st.sidebar.write(f"선택한 칼럼: {file_column}")
            file_counter_list = mt.counterVector(file_df, file_column)
            
            # 시각화 설정 (file_counter_list 사용)
            if 'file_counter_list' in locals():
                # 체크박스: 파일 데이터 빈도수 그래프 표시 여부
                show_file_graph = st.sidebar.checkbox("파일 데이터 빈도수 그래프 표시", value=True, key="file_graph_check")
                
                if show_file_graph:
                    # 슬라이더: 파일 데이터 빈도수 그래프용 단어 수
                    file_words = st.sidebar.slider("파일 데이터 단어 수", min_value=5, max_value=50, value=10, key="file_graph_slider")
                    stv.showPlot(file_counter_list, file_words)
                
                # 체크박스: 파일 데이터 워드클라우드 표시 여부
                show_file_wc = st.sidebar.checkbox("파일 데이터 워드클라우드 표시", value=True, key="file_wc_check")
                
                if show_file_wc:
                    # 슬라이더: 파일 데이터 워드클라우드용 단어 수
                    file_wc_words = st.sidebar.slider("파일 데이터 워드클라우드 단어 수", min_value=5, max_value=50, value=10, key="file_wc_slider")
                    stv.showWordCloud(file_counter_list, file_wc_words)
        else:
            st.sidebar.error(f"칼럼 '{file_column}'이 파일에 존재하지 않습니다. 다음 칼럼 중에서 선택해주세요: {', '.join(file_df.columns)}")
    


query = st.sidebar.text_input("데이터가 있는 칼럼명 ")

if query:
    if 'query' in locals():
        st.sidebar.write(f"컬럼명: {query}")
        counter_list = mt.counterVector(df_list , query) 
        
    
    #파일 올렸을때 읽는것도 필요 
    
    


# 버튼
if st.sidebar.button("파일 확인"):
    if uploaded_file is not None:
        # 텍스트 파일 기준
        content = uploaded_file.read().decode("utf-8")
        st.write("파일 내용:")
        st.text(content)
    else:
        st.warning("먼저 파일을 업로드해주세요.")





    
# 설정 영역
st.sidebar.header("설정")

# 체크박스: 빈도수 그래프 표시 여부
show_freq_graph = st.sidebar.checkbox("빈도수 그래프 표시", value=True)

if show_freq_graph == True:
    if 'counter_list' in locals():    #counter_list 가 정의되어있을 때만 실행
        # 슬라이더: 빈도수 그래프용 단어 수
        top_n_words = st.sidebar.slider("단어 수", min_value=5, max_value=50, value=10 , key="freq_slider")
        stv.showPlot(counter_list, top_n_words)


    



# 체크박스: 워드클라우드 표시 여부
show_wc = st.sidebar.checkbox("워드클라우드 표시", value=True)

if show_wc == True:
    if 'counter_list' in locals():
    # 슬라이더: 워드클라우드용 단어 수
        top_n1_words = st.sidebar.slider("단어 수", min_value=5, max_value=50, value=10, key="wc_slider")
        stv.showWordCloud(counter_list , top_n1_words)







# 설정 출력 (테스트용)
#st.sidebar.write(f"빈도수 그래프 표시 여부: {show_freq_graph}")
#st.sidebar.write(f"단어 수 설정: {top_n_words}")