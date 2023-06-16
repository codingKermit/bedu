<template>
    <div class="container5">
      <div class="center-container">
        <div class="m-select">
          <ul class="move">
            <li><a class="csc-a" href="#">강의상세</a></li>
            <li><a class="csc-a" href="#">계정설정</a></li>
            <li><a class="csc-a" href="#">기기 및 재생환경</a></li>
            <li><a class="csc-a" href="#">결제</a></li>
            <li><a class="csc-a" href="#">해지/환불</a></li>
          </ul>
        </div>
        <div class="accordion">
          <div v-for="(item, index) in items" :key="index" class="accordion-item" :class="{ active: item.isActive }">
            <div class="accordion-header" @click="toggleAccordion(index)">
              {{ item.title }}
            </div>
            <div class="accordion-content" v-show="item.isActive">
              {{ item.content }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        items: [
          {
            title: 'Q. 분야별 강의/취업 로드맵, 토픽, 챕터, 레슨 단위에 대해 알고 싶어요.',
            content: 'BEDU 강의 구조는 다음과 같이 이루어져 있어요. 레슨(강의/노트/퀴즈/과제) < 챕터 < 토픽 < 분야별 강의/취업 로드맵레슨은 동영상 강의, 노트, 퀴즈, 과제를 나타내는 가장 작은 단위의 수업이에요. 이를 묶어 놓은 것이 챕터, 그리고 챕터를 묶어 수강할 수 있도록 만든 것이 토픽입니다. 분야별 강의/취업 로드맵은 이 모든 과정을 가장 효과적으로 학습할 수 있도록 단계별로 배치한 것입니다.',
            isActive: false,
          },
          {
            title: 'Q. 동영상 강의는 몇 분 정도인가요?',
            content: '코드잇 강의는 5분 내외의 짧은 영상으로 이루져있어요. 예상 소요시간은 각 토픽 페이지에서 확인하실 수 있습니다.',
            isActive: false,
          },
          {
            title: 'Q. 강의를 듣다가 궁금증이 생기면 어떻게 해야 하나요?',
            content: '레슨 하단 질문하기를 통해 커뮤니티에 궁금증을 남기고, 다른 수강생들과 함께 해결해보세요. 커뮤니티 정책을 참고하여 커뮤니티를 200% 활용해 보세요.',
            isActive: false,
          },
          {
            title: 'Q. 강의 영상을 다운로드할 수 있나요?',
            content: '아니요. 강의 내용은 다운로드가 불가능합니다. 다만 노트북, 태플릿, 스마트 폰 등 다양한 기기에서 언제든지 코드잇 강의를 수강하실 수 있습니다.',
            isActive: false,
          },
        ],
      };
    },
    methods: {
      toggleAccordion(index) {
        this.items[index].isActive = !this.items[index].isActive;
      },
    },
  };
  </script>
  
  <style>
  .m-select {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 600px;
    height: 50px;
    background-color: rgb(240, 240, 239);
    padding: 10px;
    margin-bottom: 10px;
    text-align: center;  /* 가운데 정렬 */
  }
  
  .move {
    display: flex;
    list-style-type: none;
    padding: 0;
    margin: 0;
  }
  
  .move li {
    margin-right: 30px;
    font-size: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  
  .container5 {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  
  .center-container {
    width: 600px;
    /* 아코디언 컴포넌트의 너비 */
  }
  
  .accordion {
    width: 100%;
  }
  
  .accordion-item {
    margin-bottom: 10px;
  }
  
  .accordion-header {
    cursor: pointer;
    background-color: #e0e0e0;
    padding: 10px;
    position: sticky;
    top: 0;
    z-index: 1;
  }
  
  .accordion-content {
    padding: 10px;
    background-color: #f5f5f5;
    display: none;
  }
  
  .active .accordion-content {
    display: block;
  }
  </style>