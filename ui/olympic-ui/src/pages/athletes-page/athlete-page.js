import React, {Component} from 'react';

import {withStyles} from '@mui/styles';
import {styles} from "../../css-common";

import {
    Container,
    Grid,
    Stack,
    Avatar,
    Badge,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogContentText, TextField, DialogActions, FormControl, InputLabel, Input
} from "@mui/material";

import UploadIcon from '@mui/icons-material/Upload';

import AthleteMainInfo from "../../components/athlete-main-info/athlete-main-info";

import AthleteService from "../../services/athlete-service";
import Button from "@mui/material/Button";

class AthletePage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null,
            openImageUrlDialog: false,
            url: ""
        }
    }

    componentDidMount() {
        this.getAthleteInfo(this.props.match.params.id)
    }

    getAthleteInfo = (id) => {
        AthleteService.getAthleteById(id)
            .then(response => {
                console.log(response.data)
                this.setState({data: response.data})
            })
            .catch(e => console.log(e));
    }

    onSelectGame = (id) => {
        window.location.assign(`/game/${id}`);
    }

    uploadAthleteImageUrl = () => {
        console.log('uploading image url int db');
        AthleteService.postImgUrl(this.props.match.params.id, this.state.url)
            .then(response => {
                console.log(response.data)
                this.setState({data: response.data})
            })
            .catch(e => console.log(e));
    }

    handleImageUrlUpload = () => {
        this.setState({
            openImageUrlDialog: true
        });
    }

    handleImageUrlUploadClose = () => {
        this.setState({
            openImageUrlDialog: false
        });
    }

    handleImageUrlUploadSave = () => {
        this.handleImageUrlUploadClose();
        this.uploadAthleteImageUrl();
    }

    handleImageUrlChange = (e) => {
        this.setState({
            url: e.target.value
        });
    }

    render() {

        const {data, openImageUrlDialog, url} = this.state;

        const {classes} = this.props;

        return (
            <Container maxWidth="lg">
                <h1 style={{"text-transform":"uppercase", "text-align":"center", "margin-top": "80px"}}>Athlete Info</h1>
                <Grid container spacing={2} style={{"margin-bottom":"10px"}}>
                    <Grid item xs={4} style={{"textAlign":"center"}}>
                        <Stack direction="row" spacing={2}>
                            <Badge
                                overlap="circular"
                                anchorOrigin={{ vertical: 'bottom', horizontal: 'right'}}
                                badgeContent={
                                    <Avatar
                                        src=""
                                        sx={{ width: 35, height: 35 }}
                                        style={{border:'2px solid', backgroundColor: '#990'}}
                                        onClick={this.handleImageUrlUpload}
                                    >
                                        <UploadIcon />
                                    </Avatar>
                                }
                            >
                                <Avatar
                                    alt={data && data.surname}
                                    src={data && data.url}
                                    sx={{ width: 300, height: 300 }}
                                    style={{"margin": "10px auto"}}
                                />
                            </Badge>
                        </Stack>
                    </Grid>
                    { data &&
                        <Grid item xs={8}>
                            <AthleteMainInfo
                                data={data}
                                onSelectGame={this.onSelectGame}
                            />
                        </Grid>
                    }
                </Grid>
                <Dialog
                    open={openImageUrlDialog}
                    onClose={this.handleImageUrlUploadClose}
                    fullWidth
                    maxWidth={"sm"}
                >
                    <DialogTitle>Input athlete's image URL</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            You can change an image of this athlete. Input the URL of athlete's image here
                        </DialogContentText>
                        <FormControl
                            fullWidth
                            maxWidth={"sm"}
                        >
                            <Input id="image-url"
                                   value={url}
                                   onChange={this.handleImageUrlChange}
                                   fullWidth
                            />
                        </FormControl>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleImageUrlUploadClose}>Cancel</Button>
                        <Button onClick={this.handleImageUrlUploadSave}>Save</Button>
                    </DialogActions>
                </Dialog>
            </Container>
        );
    }

}

export default withStyles(styles)(AthletePage);