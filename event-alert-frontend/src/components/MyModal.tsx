import React, { useEffect, useState } from 'react'
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { RequestError, SuccessMessage } from '../types/types';
import { red } from '@mui/material/colors';

type ModalProps = {
    error?: RequestError | undefined;
    success?: SuccessMessage | undefined;
    closeCard: (closed: undefined) => void;
}

function MyModal(props: ModalProps) {

  const [open, setOpen] = useState<boolean>(false);

  useEffect(() => {
    console.log(props.error !== undefined)
    console.log(props.success !== undefined)
    setOpen(props.error !== undefined || props.success !== undefined);    
  }, [props.error, props.success]);

  const errorTheme = {
    background: red.A400,
    textColor: 'white',
    buttonBg: 'var(--black)'
  };

  const successTheme = {
    background: 'var(--green)',
    texColor: 'var(--black)',
    buttonBg: 'var(--black)',
    buttonText: 'white'
  }


    function closeModalCard() {
        setOpen(false);
        props.closeCard(undefined);
    }

  return (
    <div>
      <Dialog
        open={open}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title" style={{backgroundColor: `${props.error ? errorTheme.background : successTheme.background}`, color: `${props.error ? errorTheme.textColor : successTheme.texColor}`}}>
          {props.error !== undefined ? props.error.myStatus : null}
        </DialogTitle>
        <DialogContent style={{backgroundColor: `${props.error ? errorTheme.background : successTheme.background}`}}>
          <DialogContentText id="alert-dialog-description" style={{color: `${props.error ? errorTheme.textColor : successTheme.texColor}`}}>
            {props.error !== undefined ? props.error.message : props.success?.message}
          </DialogContentText>
        </DialogContent>
        <DialogActions style={{backgroundColor: `${props.error ? errorTheme.background : successTheme.background}`}}>
          <Button style={{color: `${props.error ? errorTheme.textColor : successTheme.buttonText}`, backgroundColor: `${props.error ? errorTheme.buttonBg : successTheme.buttonBg}`}} onClick={closeModalCard}>
            Schliessen
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default MyModal;