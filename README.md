# 프로젝트 간단 소개

컴퓨터 공학 전공 자체 생성 문제 또는 자격증 기출 문제를 풀 수 있는 [사이트](https://comssa.site/)   

<img width="1099" alt="image" src="https://github.com/user-attachments/assets/8cb5f257-69af-474f-945c-74ae1d9a9597" />

# 프로젝트 설계 중점

다양한 문제 유형의 확장성과 유지보수를 고려한 유연한 설계
# 설계 방법

### 1. Entity간의 상속 관계를 DB에서 구현시 Join 전략을 사용

![comssaInfra drawio](https://github.com/user-attachments/assets/96ad881d-af59-40c0-bba0-bcd990dc1a42)

### 2. QueryDsl을 사용

#### 공통된 조건으로 조회시에도 문제마다 중복되는 쿼리를 작성해야하는 문제점

- **전공 객관식** 문제를 카테고리로 조회

```java

@Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq "
        + "LEFT JOIN FETCH nq.questionChoices "
        + "WHERE nq.questionCategory IN :questionCategories ")
List<MajorMultipleChoiceQuestion> findAllByQuestionCategoriesFetchChoices(
        @Param("questionCategories") List<QuestionCategory> questionCategories
);
```

- **자격증 객관식** 문제를 카테고리로 조회

```java

@Query("SELECT DISTINCT lnq FROM LicenseMultipleChoiceQuestion  lnq "
        + "LEFT JOIN FETCH lnq.questionChoices "
        + "WHERE lnq.questionCategory IN :questionCategories ")
List<LicenseMultipleChoiceQuestion> findAllByQuestionCategoriesFetchChoices(
        @Param("questionCategories") List<QuestionCategory> questionCategories);
```


#### QueryDsl로 해결
- 문제를 조회하는 **공통된 조건** (조회할 테이블에 Where 조건을 적용)

```java
public interface SelectWhereCategoriesAndLevels<T extends Question> {

	default JPAQuery<T> selectWhereQuestionCategories(List<QuestionCategory> questionCategories) {
		return getQuestions()
			.where(
				whereCategories(
					getQuestionQClass(),
					questionCategories)
			);
	}

	//구현체에서 Select할 문제 테이블을 설정
	JPAQuery<T> getQuestions();

```
- 실제 구현체에서는 **조회할 테이블**만을 설정해준다.
```java
@Override
	public JPAQuery<LicenseMultipleChoiceQuestion> getQuestions() {
		return jpaQueryFactory.selectFrom(
QLicenseMultipleChoiceQuestion.licenseMultipleChoiceQuestion);
	}
```
- Repository에서 검색 조건을 정의하는 인터페이스를 상속하여 <br>문제의 종류마다 어떤 조건으로 검색될 수 있는지도 쉽게 알 수 있음
```java
@Repository
public class LicenseMultipleChoiceQuestionDslRepository
	extends QueryDslJpaQueryMaker<LicenseMultipleChoiceQuestion>
	implements SelectWhereCategoriesAndLevels<LicenseMultipleChoiceQuestion>,
               SelectWhereContent~~(추가될 수 있는 검색 조건)
```


### 3. 문제의 성격에 따라 인터페이스를 상속하고 행동을 정의 
- **객관식 문제**의 공통된 행동 정의
```java
public interface ChoiceBehavior {
	List<? extends QuestionChoice> getQuestionChoices();
}
```
```java
public class LicenseMultipleChoiceQuestion extends Question implements ChoiceBehavior

@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
private List<LicenseQuestionChoice> questionChoices;
//구현체에서 각각 LicenseQuestionChoice, MajorQuestionChoice를 반환하도록 구현
@Override
	public List<LicenseQuestionChoice> getQuestionChoices() {
		return questionChoices;
	}
```
- **객관식 문제**의 공통된 Response Dto에서의 사용
```java
public static <Q extends Question & ChoiceBehavior> 
ResponseMultipleChoiceQuestionDto from(Q question) {
        List<ResponseQuestionChoiceDto> questionChoices = 
            question.getQuestionChoices()
           .stream()
             ...
    }
```

# 성과

확장성 높은 설계로 인하여 빠르게 다양한 유형의 문제를 추가 할 수 있었으며<br>
배포 한 달만에 5000회 이상의 API 요청 수신

<img width="765" alt="image" src="https://github.com/user-attachments/assets/66427819-a16a-44e4-b1bd-e5667b2e5b8e" />
