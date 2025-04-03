# 네이버 검색 API 예제 -  블로그 검색 예제를 뉴스 검색 예제로 수정하여 실100행
# 네이버 검색 API 예제 - 블로그 검색

def serchNaverNews(keyword):
    import os
    import sys
    import urllib.request
    import json
    import pandas as pd
    
    #keyword 가 영문일때 한글로 바꾸어야함
    for i in range(10):
      
      start_num = i * 100 + 1

      client_id = "eHln2QR4WT1fKC_YtJxX"
      client_secret = "DuYR7loEEt"
      encText = urllib.parse.quote(keyword)# 검색어 한글로 안전하게 변환
      url = "https://openapi.naver.com/v1/search/news?query=" + encText + f"&display=100&start={start_num}"
      # url = "https://openapi.naver.com/v1/search/blog.xml?query=" + encText # XML 결과
      request = urllib.request.Request(url) #request 메세지 구성 객체 생성후 , 설정후
      request.add_header("X-Naver-Client-Id",client_id)
      request.add_header("X-Naver-Client-Secret",client_secret)
      response = urllib.request.urlopen(request) #response로 받아오기
      rescode = response.getcode()
    #받아온 정보가 정상인지 확인 정상이면 데이터 읽어오고 
      if(rescode==200):
        response_body = response.read()
        json_data = json.loads(response_body.decode('utf-8'))
        df_data = pd.DataFrame(json_data)
        return df_data
      else:
        print("Error Code:" + rescode)

#csv 저장 , (빈도 추출후 저장? 추출전 저장?)
def save_CSV(json_list , filename):
    import pandas as pd
    data_df = pd.DataFrame(json_list)
    data_df.to_csv(filename)
    print(f"{filename} Saved")




    
    


        

    
    
    
    
    
    
    
    
    
    
            


        
        
    
   
    
    
    
    
    
    
    
    
    
    






        



    

    










