import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()

	var body: some View {
		Text(greet)
	}
    
    init() {
        let path = try! FileManager.default.url(for: .libraryDirectory, in: .userDomainMask, appropriateFor: nil, create: false).path
        print("DB path:\n\"\(path)/Application Support/databases\"")
        
        let db = iOSDatabaseFactory().createDatabase()
        let repo = UserRepository(db: db)
        let user = User(UUID: "123", firstName: "John", lastName: "Doe")
        repo.create(user: user)
        print("users count: \(repo.getAll().count)")
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
