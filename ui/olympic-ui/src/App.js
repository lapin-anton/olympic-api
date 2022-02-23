import {Component} from "react";
import './App.css';
import NavBar from "./components/nav-bar/nav-bar";
import GameList from "./components/game-list/game-list";
import GameService from "./services/game-service";

class App extends Component {

  constructor(props) {
      super(props);
      this.fetchGames = this.fetchGames.bind(this);
      this.state = {
          gameList: []
      }
  }

  componentDidMount() {
      this.fetchGames();
  }

  async fetchGames() {
      await GameService.getAllGames()
          .then(response =>
              this.setState({
                  gameList: response.data
          }))
          .catch(e =>
              console.log(e)
          )
  }

  render() {
      return (
          <div className="App">
              <NavBar />
              <GameList
                  games={this.state.gameList} />
          </div>
      );
  }

}

export default App;
