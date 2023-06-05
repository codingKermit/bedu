<template>
    <div class="review-form">
        <h2>수강후기 작성</h2>
        <form @submit.prevent="submitReview">
            <div class="form-group">
                <label for="title">강좌</label>
                <input type="text" id="title" v-model="review.title" required>
            </div>
            <div class="form-group">
                <label for="writer">수강후기</label>
                <textarea id="writer" v-model="review.writer" required></textarea>
            </div>
            <div class="form-group">
                <label for="stars">별점</label>
                <div class="star-rating">
                <span v-for="star in 5" :key="star" @click="setStars(star)" :class="{ 'filled': star <= review.stars }">&#9733;</span>
                </div>
            </div>
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
                    title: '',
                    writer: '',
                    stars: 0,
                    author: ''
                }
            };
        },
        methods: {
            submitReview() {
                console.log('Submit Review:', this.review);
                this.$emit('add-review', this.review);
                this.$router.push('/review');
            },
            setStars(stars) {
                this.review.stars = stars;
            }
        }
    }
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