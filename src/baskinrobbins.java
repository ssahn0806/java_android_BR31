import java.util.*;
import java.util.function.Function;
public class baskinrobbins{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int selectNo;
		boolean state = true;
		while(state){
			System.out.println("------------------------");
			System.out.println("배스킨라빈스 31 아이스크림 제조하기");
			System.out.println("→당신의 취향대로 만들어보세요.");
			System.out.println("------------------------");
			System.out.println("1.시작하기");
			System.out.println("2.도움말");
			System.out.println("3.종료하기");
			System.out.println("------------------------");
			System.out.print("번호 입력>");
			selectNo = sc.nextInt();
			switch(selectNo){
			case 1:
				printChoice(); // 선택하기(실행)
				return;
			case 2:
				printHelp(); // 사용방법 안내
				return;
			case 3:
				state = false; // 무한루프 종료 - 프로그램 종료
			}
		}
		sc.close();
		System.out.println("프로그램을 종료합니다.");
	}
	@SuppressWarnings("resource")
	public static void printChoice() {
		Scanner scanner = new Scanner(System.in);
		Icecream icecream;
		int inputsize;
		System.out.println("----------------------------------------");
		System.out.println("배스킨라빈스에 오신것을 환영합니다. 어떤 크기를 원하시나요?");
		System.out.println("----------------------------------------");
		System.out.println("Step1 [사이즈 선택하기]");
		System.out.println("1.파인트(3가지맛 고르기)");
		System.out.println("2.쿼터(4가지맛 고르기)");
		System.out.println("3.패밀리(5가지맛 고르기)");
		System.out.println("4.하프갤런(6가지맛 고르기)");
		System.out.print("입력(1~4)>");
		inputsize = scanner.nextInt();
		System.out.println();
		System.out.println("Step2 [맛 선택하기]");
		if(inputsize==1){ // 선택한 사이즈에 따라 담을 공간을 할당할 때 생성자 참조를 활용하거나(lambda), 기존의 인스턴스 생성을 활용할 수 있음
			Function<Integer,Pints> constructor = Pints::new;
			Pints p1 = constructor.apply(3);
			p1.sizing();
			p1.making();
			p1.display();
		}
		else if(inputsize == 2){
			icecream = new Quarter(4);
			icecream.sizing();
			icecream.making();
			icecream.display();
		}
		else if(inputsize == 3){
			Function<Integer,Family> constructor = Family::new;
			Family f1 = constructor.apply(5);
			f1.sizing();
			f1.making();
			f1.display();
		}
		else if(inputsize == 4){
			icecream = new HalfGallon(6);
			icecream.sizing();
			icecream.making();
			icecream.display();
		}
	}
	@SuppressWarnings("resource")
	public static void printHelp(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Step1 : 먼저 사이즈를 선택하세요.(파인트-3가지맛,쿼터-4가지맛,패밀리-5가지맛,하프갤런-6가지맛)");
		System.out.println("Step2 : 각 사이즈에 할당된 가짓수 만큼 맛을 골라주세요.");
		System.out.println("Step3 : 당신이 선택한 사항들이 맞는지 확인해보세요.");
		System.out.print("이해가 되셨다면 이제 시작해볼까요?(y입력)");
		String input = scanner.nextLine();
		if(input.equals("y")) {
			printChoice();
		}
	}
}

abstract class Icecream {
	Menu menu;					// 선택가능한 맛의 리스트(메뉴판)
	Sizing size;				// 선택한 사이즈 정보
	Flavor<Integer> flavorNum;	// 선택한 맛의 번호를 담을 공간 - 이름과 연동하여 맛의 상세 정보를 제공
	Flavor<String> flavorName;	// 선택한 맛의 이름을 담을 공간 - 번호와 연동하여 이름을 입력해도 담을 수 있음
	public Icecream() {
	};
	public abstract void sizing();
	public abstract void making();
	public abstract void display();
}

class Pints extends Icecream{		// Pints,Quater,Family,HalfGallon은 선택한 사이즈에 따라 결정되는 아이스크림의 실체
	private int kinds;
	public Pints(int kind) {
		this.size = new Pint();
		this.kinds = kind;
		this.flavorNum = new Flavor<Integer>(kind);
		this.flavorName = new Flavor<String>(kind);
		this.menu = new Menu();
	}
	@Override
	public void sizing() {
		System.out.print(this.size.chooseSize());
	}
	@Override
	@SuppressWarnings("resource")
	public void making() {
		System.out.println("(선택할 수 있는 맛들은 다음과 같습니다.)");
		System.out.println("------------------------------------------------------------------------------------------------------");
		this.menu.printFlavor();
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("선택할 맛의 번호를 입력하거나 이름을 입력하세요.");
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<kinds;i++){
			System.out.print((i+1)+"번째 맛>");
			String input = scanner.nextLine();
			try{												
				if(Integer.parseInt(input)>=1 && Integer.parseInt(input)<=32){					//입력한 값이 정수로 변환할 수 있는 경우 번호로 맛을 추적
					this.flavorNum.push(Integer.parseInt(input)-1);
					this.flavorName.push(this.menu.nameList.get(Integer.parseInt(input)-1));
				}	
			}
			catch(NumberFormatException e){														//입력한 값이 이름으로 숫자로 변환할 수 없는 경우 hashMap을 활용해 번호 추적
				if(this.menu.menuMap.containsKey(input)){
					this.flavorName.push(input);
					this.flavorNum.push(this.menu.menuMap.get(input));
				}
			}
		}
	}
	@Override
	public void display() {
		System.out.println();
		System.out.println("Step3 [최종 확인하기]");
		System.out.println("(정보)");
		System.out.println(this.size.chooseSize());
		System.out.println();
		System.out.println("(담은 맛 시각화 및 상세정보)");
		for(int i=0;i<kinds;i++){																//선택한 순서의 역순으로 출력하여, 탑형태의 쌓아올린 층별 맛의 정보를 출력
			String name = this.flavorName.pop();
			int num = this.flavorNum.pop();
			System.out.println((kinds-i)+"층:"+name+"["+(num+1)+"]-"+this.menu.menuInfo.get(num));
		}
		System.out.println();
		System.out.println("프로그램을 종료합니다.");
	}
}

class Quarter extends Icecream{
	private int kinds;
	public Quarter(int kind) {
		this.size = new Quart();
		this.kinds = kind;
		this.flavorNum = new Flavor<Integer>(kind);
		this.flavorName = new Flavor<String>(kind);
		this.menu = new Menu();
	}
	@Override
	public void sizing() {
		System.out.print(this.size.chooseSize());
	}
	@Override
	@SuppressWarnings("resource")
	public void making() {
		System.out.println("(선택할 수 있는 맛들은 다음과 같습니다.)");
		System.out.println("------------------------------------------------------------------------------------------------------");
		this.menu.printFlavor();
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("선택할 맛의 번호를 입력하거나 이름을 입력하세요.");
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<kinds;i++){
			System.out.print((i+1)+"번째 맛>");
			String input = scanner.nextLine();
			try{
				if(Integer.parseInt(input)>=1 && Integer.parseInt(input)<=32){
					this.flavorNum.push(Integer.parseInt(input)-1);
					this.flavorName.push(this.menu.nameList.get(Integer.parseInt(input)-1));
				}	
			}
			catch(NumberFormatException e){
				if(this.menu.menuMap.containsKey(input)){
					this.flavorName.push(input);
					this.flavorNum.push(this.menu.menuMap.get(input));
				}
			}
		}
	}
	@Override
	public void display() {
		System.out.println();
		System.out.println("Step3 [최종 확인하기]");
		System.out.println("(정보)");
		System.out.println(this.size.chooseSize());
		System.out.println();
		System.out.println("(담은 맛 시각화 및 상세정보)");
		for(int i=0;i<kinds;i++){
			String name = this.flavorName.pop();
			int num = this.flavorNum.pop();
			System.out.println((kinds-i)+"층:"+name+"["+(num+1)+"]-"+this.menu.menuInfo.get(num));
		}
		System.out.println();
		System.out.println("프로그램을 종료합니다.");
	}
}

class Family extends Icecream{
	private int kinds;
	public Family(int kind) {
		this.size = new Pentagon();
		this.kinds = kind;
		this.flavorNum = new Flavor<Integer>(kind);
		this.flavorName = new Flavor<String>(kind);
		this.menu = new Menu();
	}
	@Override
	public void sizing() {
		System.out.print(this.size.chooseSize());
	}
	@Override
	@SuppressWarnings("resource")
	public void making() {
		System.out.println("(선택할 수 있는 맛들은 다음과 같습니다.)");
		System.out.println("------------------------------------------------------------------------------------------------------");
		this.menu.printFlavor();
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("선택할 맛의 번호를 입력하거나 이름을 입력하세요.");
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<kinds;i++){
			System.out.print((i+1)+"번째 맛>");
			String input = scanner.nextLine();
			try{
				if(Integer.parseInt(input)>=1 && Integer.parseInt(input)<=32){
					this.flavorNum.push(Integer.parseInt(input)-1);
					this.flavorName.push(this.menu.nameList.get(Integer.parseInt(input)-1));
				}	
			}
			catch(NumberFormatException e){
				if(this.menu.menuMap.containsKey(input)){
					this.flavorName.push(input);
					this.flavorNum.push(this.menu.menuMap.get(input));
				}
			}
		}
	}
	@Override
	public void display() {
		System.out.println();
		System.out.println("Step3 [최종 확인하기]");
		System.out.println("(정보)");
		System.out.println(this.size.chooseSize());
		System.out.println();
		System.out.println("(담은 맛 시각화 및 상세정보)");
		for(int i=0;i<kinds;i++){
			String name = this.flavorName.pop();
			int num = this.flavorNum.pop();
			System.out.println((kinds-i)+"층:"+name+"["+(num+1)+"]-"+this.menu.menuInfo.get(num));
		}
		System.out.println();
		System.out.println("프로그램을 종료합니다.");
	}
}

class HalfGallon extends Icecream{
	private int kinds;
	public HalfGallon(int kind) {
		this.size = new Gallon();
		this.kinds = kind;
		this.flavorNum = new Flavor<Integer>(kind);
		this.flavorName = new Flavor<String>(kind);
		this.menu = new Menu();
	}
	@Override
	public void sizing() {
		System.out.print(this.size.chooseSize());
	}
	@Override
	@SuppressWarnings("resource")
	public void making() {
		System.out.println("(선택할 수 있는 맛들은 다음과 같습니다.)");
		System.out.println("------------------------------------------------------------------------------------------------------");
		this.menu.printFlavor();
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("선택할 맛의 번호를 입력하거나 이름을 입력하세요.");
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<kinds;i++){
			System.out.print((i+1)+"번째 맛>");
			String input = scanner.nextLine();
			try{
				if(Integer.parseInt(input)>=1 && Integer.parseInt(input)<=32){
					this.flavorNum.push(Integer.parseInt(input)-1);
					this.flavorName.push(this.menu.nameList.get(Integer.parseInt(input)-1));
				}	
			}
			catch(NumberFormatException e){
				if(this.menu.menuMap.containsKey(input)){
					this.flavorName.push(input);
					this.flavorNum.push(this.menu.menuMap.get(input));
				}
			}
		}
	}
	@Override
	public void display() {
		System.out.println();
		System.out.println("Step3 [최종 확인하기]");
		System.out.println("(정보)");
		System.out.println(this.size.chooseSize());
		System.out.println();
		System.out.println("(담은 맛 시각화 및 상세정보)");
		for(int i=0;i<kinds;i++){
			String name = this.flavorName.pop();
			int num = this.flavorNum.pop();
			System.out.println((kinds-i)+"층:"+name+"["+(num+1)+"]-"+this.menu.menuInfo.get(num));
		}
		System.out.println();
		System.out.println("프로그램을 종료합니다.");
	}
}

@FunctionalInterface
interface Sizing{
	public String chooseSize();
}

class Pint implements Sizing{	//Pint,Quart,Pentagon,Gallon은 사이즈별로 할당되는 세부 정보를 포함(가격,중량,요약 설명)
	private int price;
	private int mass;
	private String sInfo;
	public Pint() {
		this.price = 8200;
		this.mass = 320;
	}
	@Override
	public String chooseSize(){				// Interface는 변수를 담을 수 없기 때문에, 가지고 있는 정보를 추상 메소드의 구현에서 모두 활용함
		this.sInfo = "3가지 맛을 골라먹는 재미가 있는 사이즈";
		return "<파인트>가격 :"+getPrice()+"원,중량 :"+getMass()+"g\n"+getInfo();
	}
	public int getPrice() {
		return price;
	}
	public int getMass(){
		return mass;
	}
	public String getInfo(){
		return sInfo;
	}
}

class Quart implements Sizing{
	private int price;
	private int mass;
	private String sInfo;
	public Quart() {
		this.price = 15500;
		this.mass = 620;
	}
	@Override
	public String chooseSize(){
		this.sInfo = "4가지 맛을 골라 퍼먹는 재미가 있는 사이즈";
		return "<쿼터>가격 :"+getPrice()+"원,중량 :"+getMass()+"g\n"+getInfo();
	}
	public int getPrice(){
		return price;
	}
	public int getMass(){
		return mass;
	}
	public String getInfo(){
		return sInfo;
	}
}

class Pentagon implements Sizing{
	private int price;
	private int mass;
	private String sInfo;
	
	public Pentagon() {
		this.price = 22000;
		this.mass = 960;
	}
	@Override
	public String chooseSize(){
		this.sInfo = "5가지 맛을 골라 가족이 나눠먹기 좋은 사이즈";
		return "<패밀리>가격 :"+getPrice()+"원,중량 :"+getMass()+"g\n"+getInfo();
	}
	public int getPrice(){
		return price;
	}
	public int getMass(){
		return mass;
	}
	public String getInfo(){
		return sInfo;
	}
}

class Gallon implements Sizing{
	private int price;
	private int mass;
	private String sInfo;
	public Gallon() {
		this.price = 26500;
		this.mass = 960;
	}
	@Override
	public String chooseSize(){
		this.sInfo = "6가지 맛을 골라 두고두고 먹는 재미가 있는 사이즈";
		return "<하프갤런>가격 :"+getPrice()+"원,중량 :"+getMass()+"g\n"+getInfo();
	}
	public int getPrice(){
		return price;
	}
	public int getMass(){
		return mass;
	}
	public String getInfo(){
		return sInfo;
	}
}

class Flavor<T>{										//사용자가 맛을 고를때 번호를 입력하거나, 맛의 이름(품목명)을 입력하거나 섞어서 입력해도 모두 처리 가능하도록 Generic 활용
	private T[] select;
	private int size;
	private int location;
	
	@SuppressWarnings("unchecked")
	public Flavor(int size){							// 쌓아올린 형태의 아이스크림을 보여주기 위해 Stack을 구현함
		select = (T[])(new Object[size]);
		this.size = size;
		location = 0;
	}
	public void push(T value){
		if(location == size){
			System.out.println("이미 모든 맛을 선택했습니다.");
		}
		else{
			select[location++] = value;
		}
	}
	public T pop(){
		if(location==0){
			System.out.println("선택한 맛이 더이상 없습니다.");
			return null;
		}
		else{
			return select[--location];
		}
	}
}

class Menu{												// 사용자에게 보여지는 맛의 종류를 가지고 있음
	final String[] menuName = {"아이스 호떡","아이스 붕어빵","매시업스 시리얼","오래오 쿠키 앤 크림","메이플 월넛","망고 탱고","다크 초코 나이트",
							   "북극곰 폴라베어","에스프레소 앤 크림","아몬드 봉봉봉","엄마는 외계인","쫀떡궁합","베리 그래놀라","봉쥬르,마카롱",
							   "마법사의 할로윈","민트 초콜릿 칩","슈팅스타","사랑에 빠진 딸기","초코나무 숲","뉴욕 치즈케이크","피스타치오 아몬드",
							   "베리베리 스트로베리","바람과 함께 사라지다","레인보우 샤베트","자모카 아몬드 훠지","이상한 나라의 솜사탕","초콜릿","31요거트",
							   "그린티","체리쥬빌레","바닐라","초콜릿 무스"}; 
	final String[] menuSummary = {"달콤한 호떡 아이스크림에 호떡 리본과 쫄깃한 떡, 씨앗이 어우러진 제품","달콤한 커스터드향 아이스크림과 팥 아이스크림 속에 붕어빵 큐브와 팥 리본이 쏙쏙","콘푸러스트 아이스크림, 첵스 초코 아이스크림에 달콤하게 초코 코팅한 첵스 초코 크럼블이 쏘옥!","부드러운 바닐라향 아이스크림에, 달콤하고 진한 오레오 쿠키가 듬뿍!","고소하고 향긋한 호두 아이스크림에 호두가 듬뿍 들어있는 제품","달콤상큼한 망고 시럽이 곁들여진 망고 아이스크림","초콜릿 프레첼과 함께 즐기는 진한 다크 초콜릿의 맛",
								  "폴라베어처럼 쿨~한 민트 아이스크림에 크런치가 쏙쏙!","유지방 1/2로 더욱 가볍게 즐기는 에스프레소 한잔","부드러운 바닐라향 아이스크림에 달콤한 초콜릿 리본, 초코코팅 아몬드가 듬뿍 들어간 아이스크림","밀크 초콜릿,다크 초콜릿,화이트 무스 세가지 아이스크림에 달콤 바삭한 초콜볼이 더해진 아이스크림","고소한 흑임자,인절미 아이스크림에 쫄깃한 떡리본과 바삭한 프랄린 피칸이 쏙쏙!","마스카포네 치즈 아이스크림에 라즈베리와 그래놀라가 쏘옥!","부드러운 크림치즈 아이스크림과 마카롱, 초콜릿의 달콤한 만남!",
								  "초콜릿과 민트향 아이스크림속에 마법같은 팝핑캔디가 톡톡!","쿨한 그녀들의 선택! 상쾌한 민트향에 초코칩까지!","톡톡 터지는 팝핑 캔디와 스프링클 캔디, 상큼한 체리 시럽이 들어있는 제품","크런치 초콜릿, 치즈 케이크, 스트로베리가 듬뿍 들어있는 아이스크림","2014년 아이스크림 콘테스트 1위 선정작!","부드럽게 즐기는 뉴욕식 정통 치즈케이크 아이스크림","피스타치오향과 아몬드가 만나 고소함이 두배!",
								  "새콤상큼 딸기 과육이 듬뿍!","블루베리와 딸기로 상큼함을 더한 치즈케이크 한 조각","상큼한 파인애플,오렌지,라즈베리 샤베트","깊고 풍부한 자모카 아이스크림에 고소한 아몬드와 초콜릿 훠지 시럽이 들어있는 제품","부드럽고 달콤한 솜사탕과 함께 떠나는 이상한 나라로의 여행","진하고 부드러운 정통 초콜릿 아이스크림","유산균이 살아 있는 오리지널 요거트 아이스크림",
								  "엄선된 녹차를 사용한 싱그러운 그린티 아이스크림","체리과육이 탱글탱글 씹히는 체리 아이스크림","부드럽고 깔끔한 바닐라 아이스크림","초콜릿 칩이 들어있는 진한 초콜릿 아이스크림"};
	Map<String,Integer> menuMap = new HashMap<>();		// 맛과 번호를 1:1대응하여 저장하고, 맛의 이름을 입력했을 때 번호를 추적하거나, 번호를 입력헀을 때 List를 활용해서 맛의 이름을 추적함
	List<String> menuInfo = new ArrayList<>();;			// 맛의 상세정보를 순서대로 담고 있음
	List<String> nameList;								// 맛의 이름을 순서대로 담고 있음
	public Menu() {
		for(int i=0;i<32;i++){
			menuMap.put(menuName[i],i);
			menuInfo.add(menuSummary[i]);
		}
	}
	public void printFlavor(){
		int i=0;
		nameList = Arrays.asList(menuName);
		Iterator<String> itr = nameList.iterator();
		while(itr.hasNext()){
			String key = itr.next();
			i++;
			System.out.print("["+(menuMap.get(key)+1)+"]"+key.toString()+" ");
			if(i>0 && i%7==0)
				System.out.println();
		}
	}
}