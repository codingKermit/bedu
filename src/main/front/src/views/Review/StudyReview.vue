<template>
  <div class="review-list">
    <div class="group">
      <div class="m-select">
        <ul class="mk-c-tab col8 tab-event">
          <li><a href="#">전체</a></li>
          <li><a href="#">기초강의</a></li>         
          <li><a href="#">데이터분석</a></li>
          <li><a href="#">웹 개발</a></li>
          <li><a href="#">프로그래밍 언어</a></li>
          <li><a href="#">인공지능</a></li>
          <li><a href="#">프로그래밍 교양</a></li>
          <li><a href="#">개발도구</a></li>
          <li><a href="#">컴퓨터 공학 전공 지식</a></li>
          <li><a href="#">디자인</a></li>
        </ul>
      </div>
      <div class="review-search">
        <p class="fw-bold fs-3 text-start" style="margin-top:20px">수강후기</p>
        <div class="search-bar">
          <div class="search-input">
            <input type="text" placeholder="검색어를 입력하세요" v-model="searchKeyword">
            <button @click="searchReviews">검색</button>
          </div>
        </div>
      </div>
    </div>
    <div class="review-sort" style="float: right;">
      <select v-model="sortOption" @change="sortReviews" style="border:none;">
        <option value="default">최신 순</option>
        <option value="highRating">평점 높은 순</option>
        <option value="lowRating">평점 낮은 순</option>
      </select>
    </div>

    <table class="review-table">
      <thead>
        <tr>
          <th>번호</th>
          <th style="padding-right: 10%;">강좌</th>
          <th style="padding-right: 20%;">수강후기</th>
          <th>별점</th>
          <th>작성자</th> 
        </tr>
      </thead>
      <tbody>
  <tr v-for="(review, index) in filteredReviews" :key="index">
    <td>{{ index + 1 }}</td>
    <td style="padding-right: 10%;">{{ review.title }}</td>
    <td>
      <span class="review-writer" style="padding-right: 20%;">{{ review.writer }}</span>
    </td>
    <td>
      <div class="star-rating">
        <span class="star" v-for="star in review.star" :key="star">&#9733;</span>
      </div>
    </td>
    <td>{{ review.author }}</td>
  </tr>
</tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    reviews: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      searchKeyword: '',
      fetchedReviews: [], // 빈 배열로 초기화
      sortOption: 'default',
      writer: ''
    };
  },
  computed: {
    filteredReviews() {
      if (this.searchKeyword) {
        const keyword = this.searchKeyword.toLowerCase();
        return this.fetchedReviews.filter(review =>
          review.title.toLowerCase().includes(keyword) ||
          review.author.toLowerCase().includes(keyword)
        );
      } else {
        return this.fetchedReviews;
      }
    },
  },
  methods: {
    fetchReviews() {
  axios.get('/api/reviews')
    .then(response => {
      this.fetchedReviews = response.data;
    })
    .catch(error => {
      console.error(error);
    });
},
    sortReviews() {
      switch (this.sortOption) {
        case 'highRating':
          this.fetchedReviews.sort((a, b) => b.star - a.star); // 평점이 높은 순으로 정렬
          break;
        case 'lowRating':
          this.fetchedReviews.sort((a, b) => a.star - b.star); // 평점이 낮은 순으로 정렬
          break;
        default:
          // 변경: 최신순으로 정렬 (createdAt 또는 updatedAt 필드를 사용)
          this.fetchedReviews.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
          break;
      }
    },
    searchReviews() {
      // 검색 기능 구현 (필요한 경우 추가해주세요)
    },
  },
  mounted() {
    this.fetchReviews();
  },
};
</script>

<style scoped>
.group {
  padding: 25px;
}

.mk-c-tab.col8 {
  border: 1px solid #eee;
  border-bottom: 0;
  border-right: 0;
  padding: 0;
}

.mk-c-tab {
  text-align: center;
  clear: both;
  overflow: hidden;
  margin-bottom: 20px;
}

.mk-c-tab>li {
  display: inline-block;
  width: 20%;
  text-align: center;
}

.mk-c-tab>li>a {
  display: block;
  padding: 12px 10px;
  background: #f8f8f8;
  border-right: 1px solid #eee;
  font-weight: 300;
  color: inherit;
}

.review-search {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input input {
  width: 200px;
  padding: 5px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.search-input button {
  padding: 5px 10px;
  background-color: #f8f8f8;
  border: 1px solid #ccc;
  border-radius: 4px;
  color: inherit;
  cursor: pointer;
}

a {
  text-decoration: none;
  cursor: pointer;
  color: inherit;
  font-size: inherit;
  font-weight: inherit;
}

.review-table {
  width: 100%;
  border-collapse: collapse;
}

.review-table th,
.review-table td {
  padding: 10px;
  text-align: center;
  border-bottom: 1px solid #ccc;
}

.star-rating {
  display: flex;
  justify-content: center;
  align-items: center;
}

.star-rating .star {
  color: gold;
}

.review-table .review-writer {
  display: block;
  width: 200px; 
  white-space: nowrap;
  text-overflow: ellipsis;
}
.review-write-button-container {
  display: flex;
  justify-content: flex-end;
  margin: 10px;
  padding: 10px;
}

.review-write-button {
  width: 150px;
  height: 50px;
  background-color: #f8f8f8;
  border: 1px solid #ccc;
}


</style>