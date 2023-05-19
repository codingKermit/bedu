<template>
  <div class="review-form">
    <h2>수강후기 작성</h2>
    <form @submit="submitReview">
      <!-- 강좌 입력 -->
      <div class="form-group">
        <label for="course">강좌</label>
        <input type="text" id="course" v-model="review.course" required>
      </div>
      <!-- 수강후기 입력 -->
      <div class="form-group">
        <label for="content">수강후기</label>
        <textarea id="content" v-model="review.content" required></textarea>
      </div>
      <!-- 별점 입력 -->
      <div class="form-group">
        <label for="stars">별점</label>
        <div class="star-rating">
          <span v-for="star in 5" :key="star" @click="setStars(star)" :class="{ 'filled': star <= review.stars }">&#9733;</span>
        </div>
      </div>
      <!-- 작성자 입력 -->
      <div class="form-group">
        <label for="author">작성자</label>
        <input type="text" id="author" v-model="review.author" required>
      </div>
      <button type="submit">작성 완료</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      review: {
        course: '',
        content: '',
        stars: 0,
        author: ''
      }
    };
  },
  methods: {
    submitReview() {
      // 수강후기 제출 로직을 추가하세요
      // this.review 객체에 작성된 후기 데이터를 활용할 수 있습니다
      console.log('Submit Review:', this.review);
      // 폼 제출 후 리뷰 작성 완료 처리 등을 수행할 수 있습니다
      
      // 수정된 리뷰를 배열에 추가하는 로직을 추가하세요
      this.$emit('add-review', this.review);
    },
    
    setStars(stars) {
      this.review.stars = stars;
    }
  }
};
</script>
  
  <style scoped>
  .review-form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }
  
  h2 {
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    font-weight: bold;
  }
  
  input[type="text"],
  textarea {
    width: 100%;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  .star-rating {
    font-size: 24px;
  }
  
  .star-rating span {
    color: #ccc;
    cursor: pointer;
    transition: color 0.3s;
  }
  
  .star-rating span.filled {
    color: gold;
  }
  
  button {
    padding: 10px 20px;
    background-color: #f8f8f8;
    border: 1px solid #ccc;
    border-radius: 4px;
    cursor: pointer;
  }
  </style>